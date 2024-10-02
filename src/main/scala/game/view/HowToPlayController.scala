package game.view
import game.Application
import scalafx.scene.control.{Button, Label}
import scalafx.scene.image.{Image, ImageView}
import scalafxml.core.macros.sfxml

@sfxml
class HowToPlayController(
                         private val imageView: ImageView,
                         private val instructions: Label,
                         private val previousButton: Button,
                         private val nextButton: Button
                         ) {

  private var page = 1

  def backToMain(): Unit = {
    Application.showMainMenu()
  }

  def next(): Unit = {
    page += 1
    updatePage()
  }

  def previous(): Unit = {
    page -= 1
    updatePage()
  }

  private def loadFirstPage(): Unit = {
    imageView.image = new Image(getClass.getResourceAsStream("../../images/instruction1.png"))
    instructions.text = "This is a stickman. His job is to run. Your job is to type and help him jump over boulders."
  }

  private def loadSecondPage(): Unit = {
    imageView.image = new Image(getClass.getResourceAsStream("../../images/instruction2.png"))
    instructions.text = "As the words show on screen, you have 5 seconds to type the word! " +
      "After every word typed, the time reduces by 5%."
  }

  private def loadThirdPage(): Unit = {
    imageView.image = new Image(getClass.getResourceAsStream("../../images/instruction3.png"))
    instructions.text = "Type the word before the time limit to make the stickman jump and gain a point!"
  }

  private def loadFourthPage(): Unit = {
    imageView.image = new Image(getClass.getResourceAsStream("../../images/instruction4.png"))
    instructions.text = "Fail to type the word within the time limit? " +
      "The stickman gets hit and dies, and the game ends."
  }

  private def loadFifthPage(): Unit = {
    imageView.image = new Image(getClass.getResourceAsStream("../../images/instruction5.png"))
    instructions.text = "Type your name after the game ends to save your score in 'My Stats'!"
  }

  private def updatePage(): Unit = {
    previousButton.disable = false
    nextButton.disable = false
    page match {
      case 1 =>
        loadFirstPage()
        previousButton.disable = true // First page - Disable button to go to non-existent previous page
      case 2 =>
        loadSecondPage()
      case 3 =>
        loadThirdPage()
      case 4 =>
        loadFourthPage()
      case 5 =>
        loadFifthPage()
        nextButton.disable = true // Last page - Disable button to go to non-existent next page
    }
  }

  updatePage()
}
