package game.model
import scalafx.animation.{KeyFrame, Timeline, TranslateTransition}
import scalafx.geometry.Pos
import scalafx.scene.image.{Image, ImageView}
import scalafx.util.Duration

class RunnerAnimation extends Animation {
  private val images = List(
    new Image(getClass.getResourceAsStream("../../images/runframe1.png")),
    new Image(getClass.getResourceAsStream("../../images/runframe2.png")),
    new Image(getClass.getResourceAsStream("../../images/runframe3.png")),
    new Image(getClass.getResourceAsStream("../../images/runframe4.png")),
    new Image(getClass.getResourceAsStream("../../images/runframe5.png")),
  )

  protected val _imageView = new ImageView(images.head)
  private var currentIndex = 0
  private val frameDuration = 80
  imageView.alignmentInParent = Pos.Center

  private val timeline = new Timeline {
    cycleCount = Timeline.Indefinite
    keyFrames = Seq(
      KeyFrame(Duration(frameDuration), onFinished = _ => {
        currentIndex = (currentIndex + 1) % images.size
        imageView.image = images(currentIndex) // Cycle through the list of images for running animation effect
      })
    )
  }

  def playAnimation(): Unit = {
    timeline.play()
  }

  def stopAnimation(): Unit = {
    timeline.stop()
  }

  private val jumpImages = List(
    new Image(getClass.getResourceAsStream("../../images/jumpframe1.png")),
    new Image(getClass.getResourceAsStream("../../images/jumpframe2.png")),
    new Image(getClass.getResourceAsStream("../../images/jumpframe3.png")),
    new Image(getClass.getResourceAsStream("../../images/jumpframe4.png")),
    new Image(getClass.getResourceAsStream("../../images/jumpframe5.png")),
    new Image(getClass.getResourceAsStream("../../images/jumpframe6.png")),
    new Image(getClass.getResourceAsStream("../../images/jumpframe7.png")),
    new Image(getClass.getResourceAsStream("../../images/jumpframe8.png")),
    new Image(getClass.getResourceAsStream("../../images/jumpframe9.png")),
  )

  def jump(): Unit = {
    timeline.stop() // Stops running animation for doing jumping animation
    val timeline2 = new Timeline {
      cycleCount = jumpImages.size
      currentIndex = 0
      keyFrames = Seq(
        KeyFrame(Duration(frameDuration), onFinished = _ => {
          imageView.image = jumpImages(currentIndex)
          currentIndex = (currentIndex + 1) % jumpImages.size
        })
      )
    }
    val translateTransition = new TranslateTransition(Duration(335), imageView) {
      byY = -200 // Move up to imitate jumping
      delay = Duration(50)
      autoReverse = true
      cycleCount = 2
    }
    translateTransition.play()
    timeline2.play()
    timeline2.onFinished = _ => {
      timeline.play() // After jumping, start running animation again
    }
  }

  private val fallImages = List(
    new Image(getClass.getResourceAsStream("../../images/fallframe1.png")),
    new Image(getClass.getResourceAsStream("../../images/fallframe2.png")),
    new Image(getClass.getResourceAsStream("../../images/fallframe3.png")),
    new Image(getClass.getResourceAsStream("../../images/fallframe4.png")),
    new Image(getClass.getResourceAsStream("../../images/fallframe5.png")),
    new Image(getClass.getResourceAsStream("../../images/fallframe6.png")),
    new Image(getClass.getResourceAsStream("../../images/fallframe7.png")),
  )

  def fall(): Unit = {
    timeline.stop() // Stops running animation for doing falling animation
    val timeline3 = new Timeline {
      cycleCount = fallImages.size
      delay = Duration(150)
      currentIndex = 0
      keyFrames = Seq(
        KeyFrame(Duration(frameDuration), onFinished = _ => {
          imageView.image = fallImages(currentIndex)
          currentIndex = (currentIndex + 1) % fallImages.size
        })
      )
    }
    timeline3.play()
  }
}
