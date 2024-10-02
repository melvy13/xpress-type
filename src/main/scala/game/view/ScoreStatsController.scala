package game.view
import game.Application
import game.util.ScoreTracker
import scalafx.Includes._
import scalafx.scene.control.Label
import scalafx.scene.layout.GridPane
import scalafxml.core.macros.sfxml

@sfxml
class ScoreStatsController(
                          private val gridPane: GridPane
                          ) {
  private var row = 0
  for (stat <- ScoreTracker.stats) { // Add new saved scores to the gridPane with name, difficulty & points scored
    val nameLabel = new Label(stat.name) { styleClass.add("normalText") }
    val difficultyLabel = new Label(stat.difficulty) { styleClass.add("normalText") }
    val scoreLabel = new Label(stat.score.toString) { styleClass.add("normalText") }
    gridPane.add(nameLabel, 0, row)
    gridPane.add(difficultyLabel, 1, row)
    gridPane.add(scoreLabel, 2, row)
    row += 1
    if (row > 5) {
      row = 5
      removeOldestStat() // Show latest 5 scores - Remove oldest stat if more than 5
    }
  }

  private def removeOldestStat(): Unit = {
    val children = gridPane.children
    val nodesToRemove = children.filter(node => GridPane.getRowIndex(node).toInt == 0)
    gridPane.children.removeAll(nodesToRemove)
    children.foreach { node =>
      val rowIndex = GridPane.getRowIndex(node).toInt
      if (rowIndex > 0) {
        GridPane.setRowIndex(node, rowIndex - 1) // Move up all cells by 1 row as oldest stat is removed
      }
    }
  }

  def backToMain(): Unit = {
    Application.showMainMenu()
  }
}
