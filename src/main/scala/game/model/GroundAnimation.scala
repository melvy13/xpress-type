package game.model
import scalafx.animation.{AnimationTimer, PauseTransition}
import scalafx.scene.image.{Image, ImageView}
import scalafx.util.Duration

class GroundAnimation extends Animation {
  private val image = new Image(getClass.getResourceAsStream("../../images/GroundMoonTexture.jpg"))
  private val imageWidth = image.width.value
  private val imageHeight = image.height.value
  private val speed = 5.0
  private val numberOfImages = 3 // Number of image instances to fill the screen
  protected val _imageView = new ImageView

  private val _imageViews = (0 until numberOfImages).map { i =>
    val img = new ImageView(image) {
      fitWidth = imageWidth
      fitHeight = imageHeight
      x = i * imageWidth // Position them side-by-side initially
    }
    img
  }

  private val animationTimer = AnimationTimer { _ =>
    imageViews.foreach { img =>
      img.x.value -= speed
      if (img.x.value + imageWidth < 0) { // Reposition image to the right as it moves out of bounds
        img.x = img.x.value + numberOfImages * imageWidth
      }
    }
  }

  def imageViews: Seq[ImageView] = _imageViews

  def playAnimation(): Unit = {
    animationTimer.start()
  }

  def stopAnimation(): Unit = {
    val delay = new PauseTransition(Duration(100)) {
      onFinished = _ => {
        animationTimer.stop()
      }
    }
    delay.play()
  }
}
