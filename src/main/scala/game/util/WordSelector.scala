package game.util
import java.io.File
import scala.util.Random

object WordSelector {
  private var _currentText = "Default"
  private var _difficulty = ""
  private var words = ""
  private val wordsFile = new File(getClass.getResource("../../texts/words.txt").toURI)

  def useCustomWords(words: String): Unit = {
    this.words = words
    _currentText = "Custom"
    _difficulty = "Custom"
  }

  def useDefaultWords(difficulty: String): Unit = { // Use easy/medium/hard words if specific difficulty is chosen
    _currentText = "Default"
    _difficulty = difficulty

    difficulty match {
      case "Easy" =>
        val wordGetter: WordGetter = new EasyWordGetter(wordsFile)
        words = wordGetter.words

      case "Medium" =>
        val wordGetter: WordGetter = new MediumWordGetter(wordsFile)
        words = wordGetter.words

      case "Hard" =>
        val wordGetter: WordGetter = new HardWordGetter(wordsFile)
        words = wordGetter.words
    }
  }

  def useDefaultWords(): Unit = { // Use all default words if mixed is chosen
    _currentText = "Default"
    _difficulty = "Mixed"
    val wordGetter: WordGetter = new WordGetter(wordsFile)
    words = wordGetter.words
  }

  def getRandomWord: String = {
    val wordList: List[String] = words.split("\n").toList
    wordList(Random.nextInt(wordList.length))
  }

  def currentText: String = _currentText
  def difficulty: String = _difficulty
}
