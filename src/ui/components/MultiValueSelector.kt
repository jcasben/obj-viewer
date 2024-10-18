package ui.components

import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JPanel

class MultiValueSelector(
    private val items: List<SelectorItem>,
    selectorText: String
) : JPanel() {

    init {
        layout = GridLayout(2, 1)
        border = BorderFactory.createLineBorder(Color.black)

        val panel = JPanel().apply {
            layout = GridLayout(1, items.size)
            items.forEach { add(it) }
        }
        add(panel)
        add(ActionButton(selectorText))
    }
}