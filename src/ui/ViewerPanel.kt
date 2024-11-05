package ui

import core.models.IndexedFace
import core.models.math.Vec4
import core.models.math.matrix.Mat4
import core.models.math.normToWindow
import core.utils.calculateNormal
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class ViewerPanel(
    var indexedFace: IndexedFace? = null,
    var transformationMatrix: Mat4? = null,
    var lightDirection: Vec4<Float>? = null
) : JPanel() {

    companion object {
        private val viewerPanel = ViewerPanel()
        fun getInstance(): ViewerPanel = viewerPanel
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.color = Color.CYAN

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
                val v1 = obj.vertexes[obj.indexes[i]].normToWindow(width, height)
                val v2 = obj.vertexes[obj.indexes[i + 1]].normToWindow(width, height)
                val v3 = obj.vertexes[obj.indexes[i + 2]].normToWindow(width, height)

                if (!isBackFacing(v1, v2, v3)) {
                    g.fillPolygon(
                        intArrayOf(v1.x, v2.x, v3.x),
                        intArrayOf(v1.y, v2.y, v3.y),
                        3
                    )
                }
            }
        }
    }

    private fun isBackFacing(v1: Vec4<Int>, v2: Vec4<Int>, v3: Vec4<Int>) = calculateNormal(v1, v2, v3) < 0
}