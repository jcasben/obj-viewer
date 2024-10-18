package ui

import core.models.ObjModel
import java.awt.BorderLayout
import java.awt.GraphicsEnvironment
import javax.swing.JPanel
import javax.swing.JSplitPane

class MainPanel : JPanel() {

    companion object {
        private var objModel = ObjModel.getInstance()
        fun drawObject() {
            val viewer = ViewerPanel.getInstance()
            viewer.indexedFace = objModel.indexedFace
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