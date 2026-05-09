// data/model/Detection.kt — Single bounding box detection result
package com.citrascan.app.data.model

/**
 * Represents a single object detection from the YOLOv8 model.
 *
 * @property classIndex The predicted class index (0=Canker, 1=Black Spot, 2=Greening).
 * @property className Human-readable class name.
 * @property confidence Detection confidence score in [0, 1].
 * @property boundingBox Normalized bounding box coordinates [x1, y1, x2, y2] in [0, 1].
 */
data class Detection(
    val classIndex: Int,
    val className: String,
    val confidence: Float,
    val boundingBox: BoundingBox
)

/**
 * Normalized bounding box coordinates.
 * All values are in the range [0, 1] relative to the image dimensions.
 */
data class BoundingBox(
    val x1: Float,
    val y1: Float,
    val x2: Float,
    val y2: Float
) {
    /** Width of the bounding box (normalized). */
    val width: Float get() = x2 - x1
    /** Height of the bounding box (normalized). */
    val height: Float get() = y2 - y1
    /** Center X coordinate (normalized). */
    val centerX: Float get() = (x1 + x2) / 2f
    /** Center Y coordinate (normalized). */
    val centerY: Float get() = (y1 + y2) / 2f
}
