package game
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.Includes._
import scalafx.scene.image.Image

object Application extends JFXApp {
  private val rootResource = getClass.getResource("view/MenuBar.fxml")
  private val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load()
  private val roots = loader.getRoot[javafx.scene.layout.BorderPane] // Show menu bar anywhere in app

  stage = new PrimaryStage {
    title = "Xpress Type"
    icons += new Image(getClass.getResourceAsStream("/images/icon.png"))
    maximized = true
    scene = new Scene {
      root = roots
      stylesheets = List(getClass.getResource("view/Stylesheet.css").toString)
    }
  }

  private def loadFxml(fxml: String): Unit = {
    val resource = getClass.getResource(fxml)
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val root = loader.getRoot[javafx.scene.layout.AnchorPane]
    roots.setCenter(root)
  }

  def showMainMenu(): Unit = {
    loadFxml("view/MainMenu.fxml")
  }

  def showDifficultyChoice(): Unit = {
    loadFxml("view/DifficultyChoice.fxml")
  }

  def showGameScreen(): Unit = {
    loadFxml("view/GameScreen.fxml")
  }

  def showScoreStats(): Unit = {
    loadFxml("view/ScoreStats.fxml")
  }

  def showTextLoader(): Unit = {
    loadFxml("view/TextLoader.fxml")
  }

  def showKeyboardTest(): Unit = {
    loadFxml("view/KeyboardTest.fxml")
  }

  def showHowToPlay(): Unit = {
    loadFxml("view/HowToPlay.fxml")
  }

  showMainMenu()
}