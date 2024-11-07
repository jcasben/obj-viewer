package ui

import core.models.ObjModel
import core.models.math.Vec4
import core.models.math.matrix.IdentityMat4
import core.models.math.matrix.Mat4
import java.awt.BorderLayout
import java.awt.GraphicsEnvironment
import javax.swing.JPanel
import javax.swing.JSplitPane

class MainPanel : JPanel() {

    companion object {
        private var objModel = ObjModel.getInstance()
        var transformationMatrix: Mat4 = IdentityMat4()
        var lightDirection = Vec4(0f, 0f, 0f, 1f)
        fun drawObject() {
            val viewer = ViewerPanel.getInstance()
            viewer.indexedFace = objModel.indexedFace
            viewer.transformationMatrix = transformationMatrix
            viewer.lightDirection = lightDirection
            viewer.repaint()
        }
    }

    init {
        val graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment()

        layout = BorderLayout()

        val jsp = JSplitPane(JSplitPane.HORIZONTAL_SPLIT)
        jsp.topComponent = ViewerPanel.getInstance()
        jsp.bottomComponent = ButtonsPanel.getInstance()
        jsp.setDividerLocation((graphicsEnvironment.maximumWindowBounds.width * 0.8).toInt())
        jsp.isEnabled = false
        add(jsp)

        drawObject()
    }
}