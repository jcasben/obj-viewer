package ui

import core.models.IndexedFace
import core.models.math.matrix.Mat4
import java.awt.Color
import java.awt.Graphics
import java.awt.Polygon
import javax.swing.JPanel

class ViewerPanel(
    var indexedFace: IndexedFace? = null,
    var transformationMatrix: Mat4? = null
) : JPanel() {

    companion object {
        private val viewerPanel = ViewerPanel()
        fun getInstance(): ViewerPanel = viewerPanel
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.color = Color.BLACK

        if (indexedFace != null && transformationMatrix != null) {
            val transformedIndexedFace = indexedFace!!.copy(
                vertexes = indexedFace!!.vertexes.map { transformationMatrix!!.multiply(it) }
            )
            drawObject(transformedIndexedFace, g)
        }
    }

    private fun drawObject(obj: IndexedFace, g: Graphics) {
        for (i in obj.indexes.indices step 3) {
            if (i + 2 < obj.indexes.size) {
                val vertex1 = obj.vertexes[obj.indexes[i]]
                val vertex2 = obj.vertexes[obj.indexes[i + 1]]
                val vertex3 = obj.vertexes[obj.indexes[i + 2]]

                val triangle = Polygon(
                    intArrayOf(
                        (vertex1.x * 100 + width / 2).toInt(),
                        (vertex2.x * 100 + width / 2).toInt(),
                        (vertex3.x * 100 + width / 2).toInt(),
                    ),
                    intArrayOf(
                        (-vertex1.y * 100 + height / 2).toInt(),
                        (-vertex2.y * 100 + height / 2).toInt(),
                        (-vertex3.y * 100 + height / 2).toInt(),
                    ),
                    3
                )

                g.fillPolygon(triangle)
            }
        }
    }
}