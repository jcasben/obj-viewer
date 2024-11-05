package core.models.math.matrix

import core.models.math.Vec4

open class Mat4(private val values: Array<Float>) {

    companion object {
        const val ROWS = 4
        const val COLUMNS = 4
    }

    init {
        if (values.size != ROWS * COLUMNS) {
            throw Exception()
        }
    }

    fun get(row: Int, column: Int): Float = values[row * COLUMNS + column]

    fun set(row: Int, column: Int, value: Float) {
        values[row * COLUMNS + column] = value
    }

    fun multiply(matrix: Mat4): Mat4 {
        val result = Array(ROWS * COLUMNS) { 0f }

        for (row in 0..<ROWS) {
            for (col in 0..<COLUMNS) {
                var sum = 0f
                for (k in 0..<COLUMNS) {
                    sum += this.get(row, k) * matrix.get(k, col)
                }
                result[row * COLUMNS + col] = sum
            }
        }

        return Mat4(result)
    }

    fun multiply(point: Vec4<Float>): Vec4<Float> {
        val x = get(0, 0) * point.x + get(0, 1) * point.y + get(0, 2) * point.z + get(0, 3) * point.w
        val y = get(1, 0) * point.x + get(1, 1) * point.y + get(1, 2) * point.z + get(1, 3) * point.w
        val z = get(2, 0) * point.x + get(2, 1) * point.y + get(2, 2) * point.z + get(2, 3) * point.w
        val w = get(3, 0) * point.x + get(3, 1) * point.y + get(3, 2) * point.z + get(3, 3) * point.w

        return Vec4(x, y, z, w)
    }

    override fun toString(): String {
        return "Mat4(values=${values.contentToString()})"
    }
}