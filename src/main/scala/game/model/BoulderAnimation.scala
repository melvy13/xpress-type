package game.model
import scalafx.animation.TranslateTransition
import scalafx.geometry.Pos
import scalafx.scene.image.{Image, ImageView}
import scalafx.util.Duration

class BoulderAnimation extends Animation {
  protected val _imageView = new ImageView(new Image(getClass.getResourceAsStream("../../images/boulder.png")))

  imageView.visible = false
  imageView.alignmentInParent = Pos.BottomRight

  private val translateTransition = new TranslateTransition(Duration(350), imageView) {
    byX = -2000 // To show throwing boulder to the left
  }

  def playAnimation(): Unit = {
    imageView.visible = true
    translateTransition.play()
    translateTransition.onFinished = _ => stopAnimation()
  }

  def stopAnimation(): Unit = {
    val returnToPosition = new TranslateTransition(Duration(10), imageView) {
      byX = 2000 // After throwing boulder, return it back to original position
    }
    imageView.visible = false
    returnToPosition.play()
  }
}
