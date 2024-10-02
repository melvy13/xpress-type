package game.util
import game.Application
import game.model.Score
import scalafx.scene.control.TextInputDialog
import scala.collection.mutable.ArrayBuffer

object ScoreTracker {
  private var _score: Int = 0
  private val _stats: ArrayBuffer[Score] = new ArrayBuffer[Score]

  def score: Int = _score
  def stats: ArrayBuffer[Score] = _stats
  def addScore(): Unit = _score += 1
  def resetScore(): Unit = _score = 0

  def saveScore(): Unit = {
    val dialog = new TextInputDialog(defaultValue = "") {
      initOwner(Application.stage)
      title = "Save score"
      headerText = "You can view your latest scores at 'My Stats'!"
      contentText = "Type your name:"
    }

    val result = dialog.showAndWait()
    result match {
      case Some(name) => _stats += Score(name, WordSelector.difficulty, _score)
      case None => // Do not save score if cancelled
    }
  }

  if (_stats.length > 5) {
    _stats.remove(0) // Save only the latest 5 scores - If more than 5, remove the oldest one
  }
}
