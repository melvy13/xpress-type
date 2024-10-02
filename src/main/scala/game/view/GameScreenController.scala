package game.view
import game.Application
import game.model._
import game.util.{ScoreTracker, WordSelector}
import scalafx.animation.{KeyFrame, PauseTransition, Timeline}
import scalafx.scene.control.{Button, Label, ProgressBar, TextField}
import scalafx.scene.layout.{GridPane, Pane}
import scalafx.scene.paint.Color
import scalafx.util.Duration
import scalafxml.core.macros.sfxml

@sfxml
class GameScreenController(
                          private val groundPane: Pane, // Placeholder for ground
                          private val gridPane: GridPane, // Placeholder for runner & boulder
                          private val text1: Label, // Show 3 second timer before game starts, then show score
                          private val text2: Label, // Show the word to type
                          private val text3: Label, // Show if word is typed correctly
                          private val textField: TextField, // Place to type the word
                          private val progressBar: ProgressBar, // Show time remaining to type each word
                          private val backButton: Button // Button to go back to main menu
                          ) {
  private val groundAnimation = new GroundAnimation
  private val runnerAnimation = new RunnerAnimation
  private val boulderAnimation = new BoulderAnimation
  groundPane.children = groundAnimation.imageViews
  gridPane.add(runnerAnimation.imageView, 0, 0)
  gridPane.add(boulderAnimation.imageView, 5, 0)
  ScoreTracker.resetScore()

  private val countdown = new Timeline { // 3 seconds countdown before game starts
    cycleCount = 1
    keyFrames = Seq(
      KeyFrame(Duration(1000), onFinished = _ => text1.text = "Ready in: 2"),
      KeyFrame(Duration(2000), onFinished = _ => text1.text = "Ready in: 1"),
      KeyFrame(Duration(3000), onFinished = _ => text1.text = "Score: " + ScoreTracker.score)
    )
    onFinished = _ => {
      groundAnimation.playAnimation()
      runnerAnimation.playAnimation()
      showNextWord()
      checkInput()
    }
  }

  private var time = 5.0 // Initial time of 5 seconds to type word
  private var progressBarTimeline: Option[Timeline] = None

  countdown.play()

  private def showNextWord(): Unit = {
    val word: String = WordSelector.getRandomWord
    text2.text = word
  }

  private def checkInput(): Unit = {
    textField.visible = true
    text3.visible = true
    progressBar.visible = true
    text3.textFill = Color.SkyBlue
    startProgressBar()
    textField.onKeyReleased = _ => {
      val userInput = textField.text.value
      if (userInput.equals(text2.text.value)) {
        handleCorrectText()
      } else if (text2.text.value.startsWith(userInput)) { // Show "Keep Going..." as word is halfway typed correctly
        text3.text = "Keep Going..."
        text3.textFill = Color.SkyBlue
      } else { // Show "Incorrect!" if any part of the word is typed wrongly
        text3.text = "Incorrect!"
        text3.textFill = Color.Red
      }
    }
  }

  private def handleCorrectText(): Unit = {
    text3.text = "Correct!"
    text3.textFill = Color.Green
    textField.visible = false
    ScoreTracker.addScore()
    text1.text = "Score: " + ScoreTracker.score
    runnerAnimation.jump()
    boulderAnimation.playAnimation()
    progressBarTimeline.foreach(_.stop()) // Stops the current running progress bar
    val pauseTransition = new PauseTransition(Duration(720)) { // Delay to wait for runner jump animation to finish
      onFinished = _ => {
        showNextWord()
        textField.visible = true
        textField.text = ""
        startProgressBar()
        time *= 0.95 // Reduces time by 5% for every word typed correctly
      }
    }
    pauseTransition.play()
  }

  private def handleGameOver(): Unit = {
    textField.visible = false
    text3.visible = false
    progressBar.visible = false
    text2.text = "Game Over!"
    backButton.visible = true
    runnerAnimation.fall()
    boulderAnimation.playAnimation()
    groundAnimation.stopAnimation()
  }

  private def startProgressBar(): Unit = {
    val timeline = new Timeline {
      cycleCount = 1
      autoReverse = false
      keyFrames = for (i <- 1 to 20) yield {
        KeyFrame(Duration(i * (time * 50)), onFinished = _ => {
          val remainingProgress = (20 - i).toDouble / 20
          progressBar.progress = remainingProgress

          if (remainingProgress <= 0) { // When progress bar's progress reaches 0, end the game
            handleGameOver()
          }
        })
      }
    }
    timeline.play()
    progressBarTimeline = Some(timeline)
  }

  def backToMain(): Unit = {
    ScoreTracker.saveScore()
    Application.showMainMenu()
  }
}