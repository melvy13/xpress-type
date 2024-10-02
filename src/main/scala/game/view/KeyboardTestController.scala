package game.view
import game.Application
import scalafx.scene.control.{Label, TextField}
import scalafxml.core.macros.sfxml

@sfxml
class KeyboardTestController(
                            private val keyPressed: Label,
                            private val textField: TextField
                            ){

  textField.onKeyPressed = event => {
    keyPressed.text = event.getCode.toString // Get the current key pressed & show on label
  }

  def backToMain(): Unit = {
    Application.showMainMenu()
  }
}
