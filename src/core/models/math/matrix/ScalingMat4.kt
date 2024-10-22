package core.models.math.matrix

class ScalingMat4(
    x: Float,
) : Mat4(
    arrayOf(
        x, 0f, 0f, 0f,
        0f, x, 0f, 0f,
        0f, 0f, x, 0f,
        0f, 0f, 0f, 1f,
    )
)