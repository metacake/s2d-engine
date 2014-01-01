package io.metacake.s2d.input.keyboard

import java.util
import io.metacake.core.input.{InputDeviceName, ActionTrigger}
import java.awt.event.KeyEvent
import io.metacake.s2d.input.TimeStamped
import scala.collection.JavaConversions._
import io.metacake.s2d.process.recognizers.keyboard.KeyActionRecognizer

class KeyboardActionTrigger(private val keycode: Int) extends ActionTrigger[KeyEvent] with TimeStamped {
  val recognizers: util.Collection[KeyActionRecognizer] = new util.ArrayList[KeyActionRecognizer]()

  def isTriggeredBy(event: KeyEvent): Boolean = event.getKeyCode == keycode

  def bindingDevice(): InputDeviceName = KeyboardDevice.NAME

  def bindRecognizer(recognizer: KeyActionRecognizer): KeyboardActionTrigger with TimeStamped = {
    recognizers.add(recognizer)
    this
  }

  def pressed(): Unit = for(recognizer: KeyActionRecognizer <- recognizers) recognizer.press(this.getTimeStamp)

  def released(): Unit = for(recognizer: KeyActionRecognizer <- recognizers) recognizer.release(this.getTimeStamp)
}