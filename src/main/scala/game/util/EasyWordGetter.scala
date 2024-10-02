package game.util
import java.io.File

// Get words with 5 letters or less for easy difficulty
class EasyWordGetter(_file: File) extends WordGetter(_file) {
  override def words: String = {
    val easyFiltered = super.words.split("\n").filter(_.length <= 5)
    easyFiltered.mkString("\n")
  }
}
