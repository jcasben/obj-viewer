package ui.components

import java.awt.GridLayout
import javax.swing.*

class SelectorItem(
    label: String,
) : JPanel() {

    init {
        layout = GridLayout(2, 1)

        val text = JLabel(label).apply {
            horizontalAlignment = SwingConstants.CENTER
        }
        val model = SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1)
        val valuePicker = JSpinner(model)

        add(text)
        add(valuePicker)
    }
}