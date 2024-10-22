package core.models.math.matrix

data class TranslationMat4(
    val x: Float,
    val y: Float,
    val z: Float
) : Mat4(
    arrayOf(
        1f, 0f, 0f, x,
        0f, 1f, 0f, y,
        0f, 0f, 1f, z,
        0f, 0f, 0f, 1f,
    )
)