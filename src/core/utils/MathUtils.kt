package core.utils

import core.models.math.Vec4

fun calculateNormal(v1: Vec4<Int>, v2: Vec4<Int>, v3: Vec4<Int>): Int {
    val vectorA = arrayOf(v2.x - v1.x, v2.y - v1.y, v2.z - v1.z)
    val vectorB = arrayOf(v3.x - v1.x, v3.y - v1.y, v3.z - v1.z)

    return vectorA[0] * vectorB[1] - vectorA[1] * vectorB[0]
}