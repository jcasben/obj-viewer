package ui.components

import java.awt.Color
import java.awt.event.ActionListener
import javax.swing.JButton

class ActionButton(
    label: String,
    actionListener: ActionListener? = null
) : JButton() {

    init {
        this.text = label
        this.addActionListener(actionListener)
        this.foreground = Color.WHITE
        this.background = Color.DARK_GRAY
        this.isFocusPainted = false
    }
}