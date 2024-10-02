package game.view
import game.Application
import game.util.WordSelector
import scalafxml.core.macros.sfxml

@sfxml
class DifficultyChoiceController {
  def chooseEasy(): Unit = {
    WordSelector.useDefaultWords("Easy")
    Application.showGameScreen()
  }

  def chooseMedium(): Unit = {
    WordSelector.useDefaultWords("Medium")
    Application.showGameScreen()
  }

  def chooseHard(): Unit = {
    WordSelector.useDefaultWords("Hard")
    Application.showGameScreen()
  }

  def chooseMixed(): Unit = {
    WordSelector.useDefaultWords()
    Application.showGameScreen()
  }

  def backToMain(): Unit = {
    Application.showMainMenu()
  }
}
