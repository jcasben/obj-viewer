package core.utils

import core.models.IndexedFace
import core.models.math.Vec4
import java.io.File

class ObjLoader {

    companion object {
        fun parseObj(file: String): IndexedFace {
            val vertexes = mutableListOf<Vec4<Float>>()
            val indexes = mutableListOf<Int>()

            File(file).forEachLine { line ->
                val parts = line.trim().split("\\s+".toRegex())
                when (parts[0]) {
                    "v" -> {
                        if (parts.size >= 4) {
                            vertexes.add(
                                Vec4(
                                    x = parts[1].toFloat(),
                                    y = parts[2].toFloat(),
                                    z = parts[3].toFloat(),
                                    w = 1f
                                )
                            )
                        }
                    }

                    "f" -> indexes.addAll(parts.drop(1).map { it.split("\\s+".toRegex())[0].toInt() - 1 })
                }
            }

            return IndexedFace(vertexes, indexes)
        }
    }
}