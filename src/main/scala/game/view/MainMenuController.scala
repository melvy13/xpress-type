package game.view
import game.Application
import game.model.RunnerAnimation
import game.util.WordSelector
import scalafx.geometry.Pos
import scalafx.scene.layout.GridPane
import scalafxml.core.macros.sfxml

@sfxml
class MainMenuController(
                        private val gridPane: GridPane // Placeholder for runner
                        ) {

  private val runnerAnimation = new RunnerAnimation
  private val runnerImageView = runnerAnimation.imageView
  gridPane.add(runnerImageView, 1, 1)
  runnerImageView.alignmentInParent = Pos.Center
  runnerAnimation.playAnimation()

  def playGame(): Unit = {
    if (WordSelector.currentText.equals("Custom")) { // If custom words are loaded, immediately play game
      Application.showGameScreen()
    } else { // If custom words are not loaded, use default words & choose difficulty before playing game
      Application.showDifficultyChoice()
    }
  }

  def seeScoreStats(): Unit = {
    Application.showScoreStats()
  }

  def loadOwnText(): Unit = {
    Application.showTextLoader()
  }

  def testKeyboard(): Unit = {
    Application.showKeyboardTest()
  }
}
