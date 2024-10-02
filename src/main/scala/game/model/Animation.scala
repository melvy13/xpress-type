package game.model
import scalafx.scene.image.ImageView

abstract class Animation {
  protected val _imageView: ImageView

  def imageView: ImageView = _imageView
  def playAnimation(): Unit
  def stopAnimation(): Unit
}
