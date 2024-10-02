package game.view
import game.Application
import game.util.{WordGetter, WordSelector}
import scalafx.animation.FadeTransition
import scalafx.scene.control.{Label, TextArea}
import scalafx.stage.FileChooser
import scalafx.stage.FileChooser.ExtensionFilter
import scalafx.util.Duration
import scalafxml.core.macros.sfxml

import java.io.File

@sfxml
class TextLoaderController(
                          private val fileName: Label, // Show selected file name
                          private val textArea: TextArea, // Place to type custom text / show loaded file texts
                          private val saveConfirm: Label, // Brief pop up text to confirm custom text is saved
                          private val currentText: Label // Show current text chosen: Default or Custom
                          ) {

  currentText.text = "Current Text: " + WordSelector.currentText

  def loadFile(): Unit = {
    val fileChooser: FileChooser = new FileChooser() {
      title = "Choose a text file"
      initialDirectory = new File("C:\\")
      extensionFilters.add(new ExtensionFilter("Text Files", "*.txt")) // Allow user to choose only text files
    }
    val selectedFile: File = fileChooser.showOpenDialog(Application.stage)
    if (selectedFile != null) {
      fileName.text = selectedFile.getName
      val wordGetter: WordGetter = new WordGetter(selectedFile)
      textArea.text = wordGetter.words
    } else {
      fileName.text = "No file selected"
    }
  }

  def saveText(): Unit = {
    val fadeOutTransition = new FadeTransition {
      node = saveConfirm
      duration = Duration(2000) // Show pop up save confirmed text for 2 seconds as it fades out
      fromValue = 1.0
      toValue = 0.0
      onFinished = _ => saveConfirm.visible = false
    }

    if (textArea.text.value.trim.nonEmpty) { // Use custom text as long as words exist in the textArea
      WordSelector.useCustomWords(textArea.text.value)
      currentText.text = "Current Text: " + WordSelector.currentText
      saveConfirm.text = "Saved!"
      saveConfirm.visible = true
      fadeOutTransition.play()
    } else {
      WordSelector.useDefaultWords()
      currentText.text = "Current Text: " + WordSelector.currentText
      saveConfirm.text = "Default is used."
      saveConfirm.visible = true
      fadeOutTransition.play()
    }
  }

  def reset(): Unit = { // Reset to default
    fileName.text = "No file selected"
    textArea.text = ""
    WordSelector.useDefaultWords()
    currentText.text = "Current Text: " + WordSelector.currentText
  }

  def backToMain(): Unit = {
    Application.showMainMenu()
  }
}
