package ui

import core.handlers.EventHandler
import core.models.math.Vec4
import core.models.math.matrix.*
import ui.components.ActionButton
import ui.components.MultiValueSelector
import ui.components.SelectorItem
import java.awt.GridLayout
import javax.swing.JPanel

class ButtonsPanel : JPanel() {

    companion object {
        private val buttonsPanel = ButtonsPanel()
        fun getInstance(): ButtonsPanel = buttonsPanel
    }

    private val mvTranslate: MultiValueSelector
    private val mvRotate: MultiValueSelector
    private val mvScale: MultiValueSelector
    private val mvLight: MultiValueSelector

    init {
        layout = GridLayout(4, 2, 5, 10)

        mvTranslate = MultiValueSelector(
            items = listOf(
                SelectorItem("x"),
                SelectorItem("y"),
                SelectorItem("z")
            ),
            selectorText = "Translation"
        )

        mvRotate = MultiValueSelector(
            items = listOf(
                SelectorItem("x"),
                SelectorItem("y"),
                SelectorItem("z")
            ),
            selectorText = "Rotation"
        )

        mvScale = MultiValueSelector(
            items = listOf(
                SelectorItem("x", 1.0),
            ),
            selectorText = "Scaling"
        )

        mvLight = MultiValueSelector(
            items = listOf(
                SelectorItem("x"),
                SelectorItem("y"),
                SelectorItem("z"),
            ),
            selectorText = "Light Direction"
        )

        val loadReset = JPanel().apply {
            layout = GridLayout(1, 2)
            add(ActionButton("Load", EventHandler.loadObjectHandler()))
            add(ActionButton("Reset", EventHandler.resetTransformationMatrixHandler()))
        }

        add(mvTranslate)
        add(mvLight)
        add(mvRotate)
        add(ActionButton("Set Light Direction", EventHandler.setLightHandler()))
        add(mvScale)
        add(loadReset)
        add(ActionButton("Transform", EventHandler.transformObjectHandler()))
        add(ActionButton("Set object color", EventHandler.changeObjectColorHandler()))
    }

    fun generateTransformationMatrix() {
        val translate = mvTranslate.getValues()
        val rotate = mvRotate.getValues()
        val scale = mvScale.getValues()
        val matrix =
            TranslationMat4(
                x = translate[0],
                y = translate[1],
                z = translate[2],
            ).multiply(
                ScalingMat4(
                    x = scale[0],
                )
            ).multiply(
                RotationMat4(
                    x = AxisXRotationMat4(Math.toRadians(rotate[0].toDouble())),
                    y = AxisYRotationMat4(Math.toRadians(rotate[1].toDouble())),
                    z = AxisZRotationMat4(Math.toRadians(rotate[2].toDouble()))
                )
            )
        MainPanel.transformationMatrix = matrix
    }

    fun setLightDirection() {
        val light = mvLight.getValues()
        MainPanel.lightDirection = Vec4(light[0], light[1], light[2], 1f)
    }
}