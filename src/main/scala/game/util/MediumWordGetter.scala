package game.util
import java.io.File

// Get words with 6 to 8 letters for medium difficulty
class MediumWordGetter(_file: File) extends WordGetter(_file) {
  override def words: String = {
    val mediumFiltered = super.words.split("\n").filter(word => word.length >= 6 && word.length <= 8)
    mediumFiltered.mkString("\n")
  }
}
