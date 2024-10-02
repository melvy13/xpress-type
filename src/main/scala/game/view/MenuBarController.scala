package game.view
import game.Application
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, ButtonType}
import scalafxml.core.macros.sfxml

@sfxml
class MenuBarController {
  def minimiseScreen(): Unit = {
    Application.stage.setIconified(true)
  }

  def maximiseScreen(): Unit = {
    Application.stage.maximized = true
  }

  def makeFullScreen(): Unit = {
    if (!Application.stage.fullScreen.value) {
      Application.stage.fullScreenExitHint = "Press Ctrl+F11 again to exit full screen"
      Application.stage.fullScreen = true
    } else {
      Application.stage.fullScreen = false
    }
  }

  def closeApp(): Unit = {
    val alert = new Alert(AlertType.Confirmation) {
      initOwner(Application.stage)
      title = "Exit Game"
      headerText = "Confirm to exit game?"
    }

    val confirmation = alert.showAndWait()
    confirmation match {
      case Some(ButtonType.OK) => Application.stage.close() // Close application after confirmation
      case _ => // Do nothing if cancelled
    }
  }

  def showHowToPlay(): Unit = {
    Application.showHowToPlay()
  }
}
