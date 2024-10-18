package ui

import core.handlers.EventHandler
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

    init {
        layout = GridLayout(6,1, 5, 15)

        val mvSelector = MultiValueSelector(
            items = listOf(
                SelectorItem("x"),
                SelectorItem("y"),
                SelectorItem("z")
            ),
            selectorText = "Translate"
        )

        val mvRotate = MultiValueSelector(
            items = listOf(
                SelectorItem("x"),
                SelectorItem("y"),
                SelectorItem("z")
            ),
            selectorText = "Rotate"
        )

        val mvScale = MultiValueSelector(
            items = listOf(
                SelectorItem("Scaling factor"),
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

        add(mvSelector)
        add(mvRotate)
        add(mvScale)
//        add(mvLight)
        add(ActionButton("Load", EventHandler.loadObjectHandler()))
        add(ActionButton("Reset"))
    }
}