// ui/components/BoundingBoxOverlay.kt — Canvas overlay for drawing detections
package com.citrascan.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.sp
import com.citrascan.app.data.model.Detection
import com.citrascan.app.ui.theme.ForestGreen
import com.citrascan.app.ui.theme.SeverityBad

/**
 * Canvas overlay that draws bounding boxes and class labels
 * over a camera preview or captured image.
 *
 * @param detections List of detections to draw.
 * @param modifier Modifier.
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun BoundingBoxOverlay(
    detections: List<Detection>,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        for (detection in detections) {
            val bb = detection.boundingBox
            val left = bb.x1 * canvasWidth
            val top = bb.y1 * canvasHeight
            val right = bb.x2 * canvasWidth
            val bottom = bb.y2 * canvasHeight
            val boxWidth = right - left
            val boxHeight = bottom - top

            val boxColor = when (detection.classIndex) {
                2 -> SeverityBad  // Greening (HLB) — red
                else -> ForestGreen
            }

            // Draw bounding box
            drawRect(
                color = boxColor,
                topLeft = Offset(left, top),
                size = Size(boxWidth, boxHeight),
                style = Stroke(width = 3f)
            )

            // Draw label background
            val label = "${detection.className} ${(detection.confidence * 100).toInt()}%"
            val textResult = textMeasurer.measure(
                text = AnnotatedString(label),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color.White
                )
            )
            val labelWidth = textResult.size.width.toFloat()
            val labelHeight = textResult.size.height.toFloat()

            drawRect(
                color = boxColor.copy(alpha = 0.85f),
                topLeft = Offset(left, top - labelHeight - 4f),
                size = Size(labelWidth + 12f, labelHeight + 4f)
            )

            drawText(
                textMeasurer = textMeasurer,
                text = label,
                topLeft = Offset(left + 6f, top - labelHeight - 2f),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color.White
                )
            )
        }
    }
}
