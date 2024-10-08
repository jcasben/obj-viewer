import ui.ObjectViewer
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import kotlin.system.exitProcess

fun main() {
    val frame = JFrame()
    val panel = ObjectViewer()

    panel.isVisible = true

    frame.add(panel)
    frame.size = Dimension(800, 600)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.isVisible = true

    frame.addWindowListener(object : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            exitProcess(0)
        }
    })
}