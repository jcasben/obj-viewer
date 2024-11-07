import ui.MainPanel
import java.awt.Dimension
import java.awt.Frame.MAXIMIZED_BOTH
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import kotlin.system.exitProcess

fun main() {
    val frame = JFrame()
    val panel = MainPanel()

    frame.title = "Object Viewer - COMPUTER GRAPHICS"
    frame.size = Dimension(900, 700)
    frame.extendedState = MAXIMIZED_BOTH
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.isVisible = true
    frame.add(panel)

    frame.addWindowListener(object : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            exitProcess(0)
        }
    })
}