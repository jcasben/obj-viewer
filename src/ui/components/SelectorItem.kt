package ui.components

import java.awt.GridLayout
import javax.swing.*

class SelectorItem(
    label: String,
    value: Double = 0.0
) : JPanel() {

    private val valuePicker: JSpinner

    init {
        layout = GridLayout(2, 1)

        val text = JLabel(label).apply {
            horizontalAlignment = SwingConstants.CENTER
        }
        val model = SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1)
        valuePicker = JSpinner(model)
        valuePicker.value = value

        add(text)
        add(valuePicker)
    }

    fun getSpinnerValue(): Double = valuePicker.value as Double
}