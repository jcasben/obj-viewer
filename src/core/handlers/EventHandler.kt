package core.handlers

import core.models.ObjModel
import core.models.math.Vec4
import core.models.math.matrix.IdentityMat4
import ui.ButtonsPanel
import ui.MainPanel
import java.awt.Color
import java.awt.event.ActionListener
import java.io.File
import javax.swing.JColorChooser
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

class EventHandler {
    companion object {
        private const val PATH = "./assets/"
        fun loadObjectHandler(): ActionListener {
            return ActionListener {
                val objFilter = FileNameExtensionFilter("Obj Files", "obj")

                val fileChooser = JFileChooser().apply {
                    fileSelectionMode = JFileChooser.FILES_ONLY
                    fileFilter = objFilter
                    dialogTitle = "Select the obj file that you want to visualize"
                    currentDirectory = File(PATH)
                }

                val result = fileChooser.showOpenDialog(null)

                if (result != JFileChooser.CANCEL_OPTION) {
                    val fileName: File = fileChooser.selectedFile
                    if (fileName.name.isNotBlank()) {
                        ObjModel.getInstance().path = "$PATH${fileName.name}"
                        ObjModel.regenerateIndexedFace()
                        MainPanel.transformationMatrix = IdentityMat4()
                        MainPanel.drawObject()
                    }
                }
            }
        }

        fun transformObjectHandler(): ActionListener {
            return ActionListener {
                ButtonsPanel.getInstance().generateTransformationMatrix()
                MainPanel.drawObject()
            }
        }

        fun resetTransformationMatrixHandler(): ActionListener {
            return ActionListener {
                MainPanel.transformationMatrix = IdentityMat4()
                MainPanel.drawObject()
            }
        }

        fun setLightHandler(): ActionListener {
            return ActionListener {
                ButtonsPanel.getInstance().setLightDirection()
                MainPanel.drawObject()
            }
        }

        fun changeObjectColorHandler(): ActionListener {
            return ActionListener {
                val color: Color = JColorChooser.showDialog(
                    null,
                    "Choose a color for the object",
                    MainPanel.lightingModel.getColor().toColor()
                )

                MainPanel.lightingModel.lightColor = color.toVec4()
                MainPanel.drawObject()
            }
        }

        private fun Color.toVec4(): Vec4 {
            return Vec4(this.red / 255f, this.green / 255f, this.blue / 255f, 1f)
        }
    }
}