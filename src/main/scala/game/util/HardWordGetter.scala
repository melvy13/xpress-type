package game.util
import java.io.File

// Get words with 9 letters or more for hard difficulty
class HardWordGetter(_file: File) extends WordGetter(_file) {
  override def words: String = {
    val mediumFiltered = super.words.split("\n").filter(_.length >= 9)
    mediumFiltered.mkString("\n")
  }
}
