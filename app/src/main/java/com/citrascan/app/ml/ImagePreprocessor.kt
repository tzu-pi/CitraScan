// ml/ImagePreprocessor.kt — Bitmap to ONNX tensor conversion
package com.citrascan.app.ml

import android.graphics.Bitmap
import java.nio.FloatBuffer
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Preprocesses camera/gallery images for YOLOv8 inference.
 *
 * Performs:
 * 1. Resize to 640×640
 * 2. Normalize pixel values to [0, 1]
 * 3. Convert from HWC to CHW layout (channels first)
 * 4. Pack into a FloatBuffer suitable for OnnxTensor creation
 */
@Singleton
class ImagePreprocessor @Inject constructor() {

    companion object {
        /** YOLOv8 input size. */
        const val INPUT_SIZE = 640
        /** Number of channels (RGB). */
        const val CHANNELS = 3
    }

    /**
     * Converts a Bitmap into a float array in CHW format, normalized to [0, 1].
     *
     * @param bitmap Source image (any size).
     * @return FloatBuffer of shape [1, 3, 640, 640] ready for ONNX Runtime.
     */
    fun preprocess(bitmap: Bitmap): FloatBuffer {
        val resized = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, true)
        val pixels = IntArray(INPUT_SIZE * INPUT_SIZE)
        resized.getPixels(pixels, 0, INPUT_SIZE, 0, 0, INPUT_SIZE, INPUT_SIZE)

        val buffer = FloatBuffer.allocate(1 * CHANNELS * INPUT_SIZE * INPUT_SIZE)

        // CHW layout: all R values, then all G, then all B
        val channelSize = INPUT_SIZE * INPUT_SIZE
        for (i in pixels.indices) {
            val pixel = pixels[i]
            // Red channel
            buffer.put(i, ((pixel shr 16) and 0xFF) / 255.0f)
            // Green channel
            buffer.put(channelSize + i, ((pixel shr 8) and 0xFF) / 255.0f)
            // Blue channel
            buffer.put(2 * channelSize + i, (pixel and 0xFF) / 255.0f)
        }

        buffer.rewind()

        if (resized != bitmap) {
            resized.recycle()
        }

        return buffer
    }

    /**
     * Returns the input tensor shape for the ONNX model.
     */
    fun getInputShape(): LongArray = longArrayOf(1, CHANNELS.toLong(), INPUT_SIZE.toLong(), INPUT_SIZE.toLong())
}
