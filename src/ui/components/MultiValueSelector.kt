package ui.components

import java.awt.Color
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants

class MultiValueSelector(
    private val items: List<SelectorItem>,
    selectorText: String
) : JPanel() {

    init {
        layout = GridLayout(2, 1)

        val label = JLabel(selectorText).apply {
            horizontalAlignment = SwingConstants.CENTER
            border = BorderFactory.createLineBorder(Color.black, 2)
        }
        val panel = JPanel().apply {
            layout = GridLayout(1, items.size)
            items.forEach { add(it) }
        }
        add(label)
        add(panel)
    }

    fun getValues(): List<Float> = items.map { it.getSpinnerValue().toFloat() }
}