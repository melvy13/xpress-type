package game.util
import java.io.File
import scala.io.Source

// Get all words from file regardless of length for mixed difficulty
class WordGetter(file: File) {
  private val lines = Source.fromFile(file).getLines()
  private val stringBuilder = new StringBuilder()
  for (line <- lines) {
    stringBuilder.append(line)
    if (lines.hasNext) {
      stringBuilder.append("\n")
    }
  }

  protected val _words: String = stringBuilder.toString()

  def words: String = _words
}
