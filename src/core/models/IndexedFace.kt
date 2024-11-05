package core.models

import core.models.math.Vec4

data class IndexedFace(
    var vertexes: List<Vec4<Float>>,
    val indexes: List<Int>
)