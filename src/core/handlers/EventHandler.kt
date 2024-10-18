package core.handlers

import core.models.ObjModel
import ui.MainPanel
import java.awt.event.ActionListener
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

class EventHandler {
    companion object {
        fun loadObjectHandler(): ActionListener {
            return ActionListener {
                val objFilter = FileNameExtensionFilter("Obj Files", "obj")

                val fileChooser = JFileChooser().apply {
                    fileSelectionMode = JFileChooser.FILES_ONLY
                    fileFilter = objFilter
                    dialogTitle = "Select the obj file that you want to visualize"
                    currentDirectory = File("./assets/")
                }

                val result = fileChooser.showOpenDialog(null)

                if (result != JFileChooser.CANCEL_OPTION) {
                    val fileName: File = fileChooser.selectedFile
                    if (fileName.name.isNotBlank()) {
                        ObjModel.getInstance().path = fileName.name
                        ObjModel.regenerateIndexedFace()
                        MainPanel.drawObject()
                    }
                }
            }
        }
    }
}