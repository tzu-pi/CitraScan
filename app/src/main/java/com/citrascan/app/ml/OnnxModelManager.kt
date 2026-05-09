// ml/OnnxModelManager.kt — ONNX Runtime session manager
package com.citrascan.app.ml

import android.content.Context
import android.util.Log
import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.nio.FloatBuffer
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages ONNX Runtime model loading and inference sessions.
 *
 * Models are copied from assets to internal storage on first use,
 * then loaded from the file system for reliable, memory-efficient access.
 */
@Singleton
class OnnxModelManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val ortEnvironment: OrtEnvironment
) {

    companion object {
        private const val TAG = "OnnxModelManager"
        /** Fruit detection model filename. */
        const val FRUITS_MODEL = "models/citrus_yolov8s_fruits_best.onnx"
        /** Leaf detection model filename. */
        const val LEAVES_MODEL = "models/citrus_yolov8s_leaves_best.onnx"
    }

    /**
     * The scan mode determining which model to load.
     */
    enum class ScanMode {
        FRUIT, LEAF
    }

    /**
     * Copies the model from assets to internal storage if it doesn't already exist
     * or if the existing file is empty/corrupted, then returns the file.
     */
    private fun getModelFile(assetPath: String): File {
        val fileName = assetPath.replace("/", "_")
        val file = File(context.filesDir, fileName)

        // Re-copy if file doesn't exist or is suspiciously small (corrupted)
        if (!file.exists() || file.length() < 1000) {
            Log.d(TAG, "Copying model from assets: $assetPath")
            try {
                context.assets.open(assetPath).use { input ->
                    file.outputStream().use { output ->
                        input.copyTo(output, bufferSize = 65536)
                    }
                }
                Log.d(TAG, "Model copied to: ${file.absolutePath} (${file.length()} bytes)")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to copy model from assets: $assetPath", e)
                // Delete potentially corrupt partial file
                file.delete()
                throw e
            }
        }
        return file
    }

    /**
     * Creates an ONNX session, trying file-path loading first, then byte-array fallback.
     */
    private fun createModelSession(assetPath: String): OrtSession {
        // Log all assets in models folder for debugging
        try {
            val assets = context.assets.list("models")
            Log.d(TAG, "Assets in 'models/': ${assets?.joinToString(", ") ?: "null"}")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to list assets in 'models/'", e)
        }

        // Attempt 1: Copy to internal storage and load from file path
        try {
            val modelFile = getModelFile(assetPath)
            if (modelFile.exists() && modelFile.length() > 0) {
                Log.d(TAG, "Loading model from file: ${modelFile.absolutePath} (${modelFile.length()} bytes)")
                return ortEnvironment.createSession(modelFile.absolutePath)
            }
        } catch (e: Exception) {
            Log.w(TAG, "File-path loading failed for $assetPath: ${e.message}")
        }

        // Attempt 2: Load directly from assets as byte array
        try {
            Log.d(TAG, "Loading model from assets as byte array: $assetPath")
            val modelBytes = context.assets.open(assetPath).use { it.readBytes() }
            Log.d(TAG, "Read ${modelBytes.size} bytes from assets")
            if (modelBytes.isEmpty()) {
                throw IllegalStateException("Model asset is empty: $assetPath")
            }
            return ortEnvironment.createSession(modelBytes)
        } catch (e: Exception) {
            Log.e(TAG, "Byte-array loading also failed for $assetPath", e)
            throw e
        }
    }

    /**
     * Runs inference on the given input buffer using the specified model.
     *
     * @param inputBuffer Preprocessed image data as a FloatBuffer.
     * @param inputShape Shape of the input tensor (e.g., [1, 3, 640, 640]).
     * @param scanMode Whether to use the fruit or leaf model.
     * @return Raw float array output from the model.
     * @throws ModelLoadException If the model file is missing or fails to load.
     * @throws InferenceException If inference execution fails.
     */
    fun runInference(
        inputBuffer: FloatBuffer,
        inputShape: LongArray,
        scanMode: ScanMode
    ): FloatArray {
        val assetPath = when (scanMode) {
            ScanMode.FRUIT -> FRUITS_MODEL
            ScanMode.LEAF -> LEAVES_MODEL
        }

        val session = try {
            createModelSession(assetPath)
        } catch (e: Exception) {
            // Build detailed error message including root cause
            val rootCause = generateSequence(e as Throwable) { it.cause }.last()
            val detail = if (rootCause !== e) {
                "${e.message} → Root cause: ${rootCause.javaClass.simpleName}: ${rootCause.message}"
            } else {
                "${e.javaClass.simpleName}: ${e.message}"
            }
            Log.e(TAG, "All model loading attempts failed: $detail", e)
            throw ModelLoadException(detail, e)
        }

        return try {
            session.use { sess ->
                val inputName = sess.inputNames.first()
                val tensor = OnnxTensor.createTensor(ortEnvironment, inputBuffer, inputShape)

                tensor.use {
                    val results = sess.run(mapOf(inputName to tensor))
                    results.use {
                        val outputTensor = results[0] as OnnxTensor
                        @Suppress("UNCHECKED_CAST")
                        val rawOutput = outputTensor.value

                        // Handle different output shapes
                        when (rawOutput) {
                            is Array<*> -> {
                                // Shape [1, 7, 8400] → Array<Array<FloatArray>>
                                @Suppress("UNCHECKED_CAST")
                                val output3d = rawOutput as Array<Array<FloatArray>>
                                val rows = output3d[0]
                                val totalSize = rows.sumOf { it.size }
                                val flat = FloatArray(totalSize)
                                var offset = 0
                                for (row in rows) {
                                    row.copyInto(flat, offset)
                                    offset += row.size
                                }
                                flat
                            }
                            is FloatArray -> rawOutput
                            else -> throw InferenceException(
                                "Unexpected output type: ${rawOutput?.javaClass?.name}"
                            )
                        }
                    }
                }
            }
        } catch (e: ModelLoadException) {
            throw e
        } catch (e: InferenceException) {
            throw e
        } catch (e: Exception) {
            throw InferenceException("Inference failed: ${e.javaClass.simpleName}: ${e.message}", e)
        }
    }
}

/** Exception thrown when a model file cannot be loaded. */
class ModelLoadException(message: String, cause: Throwable? = null) : Exception(message, cause)

/** Exception thrown when model inference fails. */
class InferenceException(message: String, cause: Throwable? = null) : Exception(message, cause)
