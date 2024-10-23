package ui

import core.handlers.EventHandler
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

    init {
        layout = GridLayout(6, 1, 5, 15)

        mvTranslate = MultiValueSelector(
            items = listOf(
                SelectorItem("x"),
                SelectorItem("y"),
                SelectorItem("z")
            ),
            selectorText = "Translate"
        )

        mvRotate = MultiValueSelector(
            items = listOf(
                SelectorItem("x"),
                SelectorItem("y"),
                SelectorItem("z")
            ),
            selectorText = "Rotate"
        )

        mvScale = MultiValueSelector(
            items = listOf(
                SelectorItem("Scaling factor", 1.0),
            ),
            selectorText = "Scale"
        )

//        val mvLight = MultiValueSelector(
//            items = listOf(
//                SelectorItem("x"),
//                SelectorItem("y"),
//                SelectorItem("z"),
//            ),
//            selectorText = "Light Position"
//        )

        add(mvTranslate)
        add(mvRotate)
        add(mvScale)
//        add(mvLight)
        add(ActionButton("Load", EventHandler.loadObjectHandler()))
        add(ActionButton("Reset", EventHandler.resetTransformationMatrixHandler()))
    }

    fun generateTransformationMatrix() {
        val translate = mvTranslate.getValues()
        val rotate = mvRotate.getValues()
        val scale = mvScale.getValues()
        val matrix = TranslationMat4(
            x = translate[0],
            y = translate[1],
            z = translate[2],
        ).multiply(
            ScalingMat4(
                x = scale[0],
            )
        ).multiply(
            AxisZRotationMat4(
                angle = Math.toRadians(rotate[2].toDouble())
            )
        ).multiply(
            AxisXRotationMat4(
                angle = Math.toRadians(rotate[0].toDouble())
            )
        ).multiply(
            AxisYRotationMat4(
                angle = Math.toRadians(rotate[1].toDouble())
            )
        )
        MainPanel.transformationMatrix = matrix
    }
}