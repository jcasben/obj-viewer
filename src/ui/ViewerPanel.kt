package ui

import core.lighting.LightingModel
import core.models.IndexedFace
import core.models.math.Vec4
import core.models.math.matrix.Mat4
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class ViewerPanel(
    var indexedFace: IndexedFace? = null,
    var transformationMatrix: Mat4? = null,
    var lightingModel: LightingModel? = null,
) : JPanel() {

    private lateinit var viewMatrix: Mat4
    private lateinit var projectionMatrix: Mat4
    private val target = Vec4(0f, 0f, 0f, 1f)
    private val up = Vec4(0f, -1f, 0f, 0f)

    companion object {
        private val viewerPanel = ViewerPanel()
        fun getInstance(): ViewerPanel = viewerPanel
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.color = Color.BLACK

        if (indexedFace != null && transformationMatrix != null) {
            var transformedIndexedFace = indexedFace!!.copy(
                vertexes = indexedFace!!.vertexes.map { transformationMatrix!!.multiply(it) }
            )

            viewMatrix = lookAt(lightingModel!!.getCameraPos(), target, up)
            projectionMatrix = perspective(fov = 30f, aspect = (width / height).toFloat(), near = 0.1f, far = 50f)

//            transformedIndexedFace = transformedIndexedFace.copy(
//                vertexes = transformedIndexedFace.vertexes.map {
//                    projectionMatrix.multiply(viewMatrix).multiply(it)
//                }
//            )
            drawObject(transformedIndexedFace, g)
        }
    }

    private fun drawObject(obj: IndexedFace, g: Graphics) {
        val g2d = g as Graphics2D
        for (i in obj.indexes.indices step 3) {
            if (i + 2 < obj.indexes.size) {
                val nv1 = obj.vertexes[obj.indexes[i]]
                val nv2 = obj.vertexes[obj.indexes[i + 1]]
                val nv3 = obj.vertexes[obj.indexes[i + 2]]

                val v1 = obj.vertexes[obj.indexes[i]].normToWindow(width, height)
                val v2 = obj.vertexes[obj.indexes[i + 1]].normToWindow(width, height)
                val v3 = obj.vertexes[obj.indexes[i + 2]].normToWindow(width, height)

                if (!isBackFacing(v1, v2, v3)) {
                    val color = lightingModel?.calculateBlinnPhongColor(nv1, nv2, nv3, viewMatrix)
                    g2d.color = color
                    g2d.fillPolygon(
                        intArrayOf(v1.x.toInt(), v2.x.toInt(), v3.x.toInt()),
                        intArrayOf(v1.y.toInt(), v2.y.toInt(), v3.y.toInt()),
                        3
                    )
                }
            }
        }
    }

    private fun isBackFacing(v1: Vec4, v2: Vec4, v3: Vec4): Boolean {
        val a = arrayOf(v2.x - v1.x, v2.y - v1.y, v2.z - v1.z)
        val b = arrayOf(v3.x - v1.x, v3.y - v1.y, v3.z - v1.z)
        val normal = a[0] * b[1] - a[1] * b[0]

        return normal > 0f
    }

    private fun perspective(fov: Float, aspect: Float, near: Float, far: Float): Mat4 {
        val tanHalfFov = kotlin.math.tan(fov / 2f)
        return Mat4(
            arrayOf(
                1f / (aspect * tanHalfFov), 0f, 0f, 0f,
                0f, 1f / tanHalfFov, 0f, 0f,
                0f, 0f, -(far + near) / (far - near), -1f,
                0f, 0f, -(2f * far * near) / (far - near), 0f
            )
        )
    }

    private fun lookAt(cameraPos: Vec4, target: Vec4, up: Vec4): Mat4 {
        val forward = (target.subtract(cameraPos)).normalize()
        val right = up.cross(forward).normalize()
        val upCorrected = forward.cross(right)

        return Mat4(
            arrayOf(
                right.x, upCorrected.x, forward.x, 0f,
                right.y, upCorrected.y, forward.y, 0f,
                right.z, upCorrected.z, forward.z, 0f,
                -right.dot(cameraPos), -upCorrected.dot(cameraPos), -forward.dot(cameraPos), 1f
            )
        )
    }
}