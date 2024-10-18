package ui

import core.handlers.EventHandler
import ui.components.MultiValueSelector
import ui.components.SelectorItem
import java.awt.Color
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JPanel

class ButtonsPanel : JPanel() {

    companion object {
        private val buttonsPanel = ButtonsPanel()
        fun getInstance(): ButtonsPanel = buttonsPanel
    }

    init {
        layout = GridLayout(6,1, 5, 15)

        val resetButton = JButton().apply {
            text = "Reset"
            foreground = Color.WHITE
            background = Color.DARK_GRAY
        }

        val loadButton = JButton().apply {
            text = "Load"
            foreground = Color.WHITE
            background = Color.DARK_GRAY
        }
        loadButton.addActionListener(EventHandler.loadObjectHandler())

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
        add(loadButton)
        add(resetButton)
    }
}