package io.metacake.s2d.input.window

import java.awt.Dimension

import io.metacake.core.input.{InputDeviceName, ActionTrigger}
import io.metacake.s2d.process.recognizers.window.WindowResizeRecognizer

class WindowActionTrigger extends ActionTrigger[Dimension] {

  override def isTriggeredBy(event: Dimension): Boolean = true

  def resize(size: Dimension): Unit = WindowDevice.Recognizer.resize(size)

  override def bindingDevice(): InputDeviceName = WindowDevice.Name
}