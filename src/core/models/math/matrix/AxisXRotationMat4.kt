package core.models.math.matrix

import kotlin.math.cos
import kotlin.math.sin

class AxisXRotationMat4(angle: Double) : Mat4(
    arrayOf(
        1f, 0f, 0f, 0f,
        0f, cos(angle).toFloat(), -sin(angle).toFloat(), 0f,
        0f, sin(angle).toFloat(), cos(angle).toFloat(), 0f,
        0f, 0f, 0f, 1f,
    )
)