package io.metacake.s2d.input.mouse

import io.metacake.core.input.{InputDeviceName, ActionTrigger}
import java.util
import io.metacake.s2d.process.recognizers.mouse.MouseActionRecognizer
import scala.collection.JavaConversions._

class MouseActionTrigger(private val buttonCode: Either[Int,MouseMotionType]) extends ActionTrigger[Either[Int,MouseMotionType]] {

  val recognizers: util.Collection[MouseActionRecognizer] = new util.ArrayList[MouseActionRecognizer]()

  def isTriggeredBy(eventType: Either[Int,MouseMotionType]): Boolean = eventType == buttonCode

  def bindingDevice(): InputDeviceName = MouseDevice.NAME

  def bindRecognizer(recognizer: MouseActionRecognizer): MouseActionTrigger = {
    recognizers.add(recognizer)
    this
  }

  def pressed(x: Int, y: Int, weight: Long): Unit = {
    for(recognizer: MouseActionRecognizer <- recognizers) recognizer.press(weight, x, y)
  }

  def released(x: Int, y: Int, weight: Long): Unit = {
    for(recognizer: MouseActionRecognizer <- recognizers) recognizer.release(weight, x, y)
  }
}