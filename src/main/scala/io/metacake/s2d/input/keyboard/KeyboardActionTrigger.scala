package io.metacake.s2d.input.keyboard

import java.util
import io.metacake.core.input.{InputDeviceName, ActionTrigger}
import java.awt.event.KeyEvent
import io.metacake.s2d.input.TimeStamped
import scala.collection.JavaConversions._
import io.metacake.s2d.process.recognizers.keyboard.KeyActionRecognizer

class KeyboardActionTrigger(private val keycode: Int) extends ActionTrigger[KeyEvent] {
  val recognizers: util.Collection[KeyActionRecognizer] = new util.ArrayList[KeyActionRecognizer]()

  def isTriggeredBy(event: KeyEvent): Boolean = event.getKeyCode == keycode

  def bindingDevice(): InputDeviceName = KeyboardDevice.NAME

  def bindRecognizer(recognizer: KeyActionRecognizer): KeyboardActionTrigger = {
    recognizers.add(recognizer)
    this
  }

  def pressed(time: Long): Unit = for(recognizer: KeyActionRecognizer <- recognizers) recognizer.press(time)

  def released(time: Long): Unit = for(recognizer: KeyActionRecognizer <- recognizers) recognizer.release(time)
}