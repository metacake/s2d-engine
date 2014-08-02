package io.metacake.s2d.process.recognizers.window

import java.awt.Dimension

import io.metacake.core.process.{ActionRecognizerName, ActionRecognizer}

class WindowResizeRecognizer extends ActionRecognizer {
  private var newSize: Dimension = new Dimension(0, 0)

  override def getName: ActionRecognizerName = new ActionRecognizerName("Window Resize Recognizer")

  override def triggerWeight(): Int = 0

  override def forgetActions(): Unit = newSize = new Dimension(0, 0)

  override def wasTriggered(): Boolean = 0 < newSize.getHeight + newSize.getWidth

  def getSize: Dimension = {
    val actual = newSize
    newSize = new Dimension(0, 0)
    actual
  }

  def resize(size: Dimension): Unit = newSize = size
}