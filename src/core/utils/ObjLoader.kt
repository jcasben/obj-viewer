package core.utils

import core.structures.IndexedFace
import core.structures.Vec4
import java.io.File

class ObjLoader(private val file: String) {

    fun parseObj(): IndexedFace {
        val vertexes = mutableListOf<Vec4>()
        val indexes = mutableListOf<Int>()

        File(file).forEachLine { line ->
            val parts = line.trim().split("\\s+".toRegex())
            when (parts[0]) {
                "v" -> {
                    if (parts.size >= 4) {
                        vertexes.add(
                            Vec4(
                                x = parts[1].toFloat(),
                                y = parts[2].toFloat()
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