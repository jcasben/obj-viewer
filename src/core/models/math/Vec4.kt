package core.models.math

import kotlin.math.sqrt

data class Vec4(
    var x: Float,
    var y: Float,
    var z: Float,
    var w: Float
) {
    fun add(v: Vec4): Vec4 {
        return Vec4(
            x = this.x + v.x,
            y = this.y + v.y,
            z = this.z + v.z,
            w = 1f
        )
    }

    fun subtract(v: Vec4): Vec4 {
        return Vec4(
            x = this.x - v.x,
            y = this.y - v.y,
            z = this.z - v.z,
            w = 1f
        )
    }

    fun multiply(f: Float) = Vec4(this.x * f, this.y * f, this.z * f, this.w)


    fun dot(v: Vec4): Float = (this.x * v.x) + (this.y * v.y) + (this.z * v.z)

    fun cross(v: Vec4): Vec4 {
        return Vec4(
            x = this.y * v.z - this.z * v.y,
            y = this.z * v.x - this.x * v.z,
            z = this.x * v.y + this.y * v.x,
            w = 1f
        )
    }

    fun normalize(): Vec4 {
        val length = sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z))
        return Vec4(this.x / length, this.y / length, this.z / length, 1f)
    }

    fun normToWindow(width: Int, height: Int): Vec4 {
        return Vec4(
            x = (this.x * 100f + width / 2f),
            y = (-this.y * 100f + height / 2f),
            z = this.z * 100f,
            w = 1f
        )
    }
}

