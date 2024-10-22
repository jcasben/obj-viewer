package ui.components

import core.handlers.EventHandler
import java.awt.Color
import java.awt.GridLayout
import javax.swing.BorderFactory
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
        add(ActionButton(selectorText, EventHandler.transformObjectHandler()))
    }

    fun getValues(): List<Float> = items.map { it.getSpinnerValue().toFloat() }
}