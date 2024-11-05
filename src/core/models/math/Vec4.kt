package core.models.math

data class Vec4<T: Number>(
    var x: T,
    var y: T,
    var z: T,
    var w: T
)

fun Vec4<Float>.normToWindow(width: Int, height: Int): Vec4<Int> {
    return Vec4(
        x = (this.x * 100 + width / 2).toInt(),
        y = (-this.y * 100 + height / 2).toInt(),
        z = (this.z * 100 + width / 2).toInt(),
        w = 1
    )
}