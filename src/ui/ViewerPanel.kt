package ui

import core.models.IndexedFace
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class ViewerPanel(var indexedFace: IndexedFace? = null) : JPanel() {

    companion object {
        private val viewerPanel = ViewerPanel()
        fun getInstance(): ViewerPanel = viewerPanel
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.color = Color.BLACK

        if (indexedFace != null) {
            drawObject(indexedFace!!, g)
        }
    }

     private fun drawObject(obj: IndexedFace, g: Graphics) {
        for (i in obj.indexes.indices step 3) {
            if (i + 2 < obj.indexes.size) {
                val vertex1 = obj.vertexes[obj.indexes[i]]
                val vertex2 = obj.vertexes[obj.indexes[i + 1]]
                val vertex3 = obj.vertexes[obj.indexes[i + 2]]

                g.drawLine(
                    (vertex1.x * 100 + width / 2).toInt(),
                    (-vertex1.y * 100 + height / 2).toInt(),
                    (vertex2.x * 100 + width / 2).toInt(),
                    (-vertex2.y * 100 + height / 2).toInt()
                )

                g.drawLine(
                    (vertex2.x * 100 + width / 2).toInt(),
                    (-vertex2.y * 100 + height / 2).toInt(),
                    (vertex3.x * 100 + width / 2).toInt(),
                    (-vertex3.y * 100 + height / 2).toInt()
                )

                g.drawLine(
                    (vertex3.x * 100 + width / 2).toInt(),
                    (-vertex3.y * 100 + height / 2).toInt(),
                    (vertex1.x * 100 + width / 2).toInt(),
                    (-vertex1.y * 100 + height / 2).toInt()
                )
            }
        }
    }
}