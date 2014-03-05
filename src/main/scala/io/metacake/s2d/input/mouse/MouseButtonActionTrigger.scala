package io.metacake.s2d.input.mouse

import io.metacake.core.input.{InputDeviceName, ActionTrigger}
import java.awt.event.MouseEvent
import io.metacake.s2d.input.TimeStamped
import java.util
import io.metacake.s2d.process.recognizers.mouse.MouseButtonActionRecognizer
import scala.collection.JavaConversions._

class MouseButtonActionTrigger(private val buttonCode: Either[Int,MouseMotionType]) extends ActionTrigger[Either[Int,MouseMotionType]] {

  val recognizers: util.Collection[MouseButtonActionRecognizer] = new util.ArrayList[MouseButtonActionRecognizer]()

  def isTriggeredBy(eventType: Either[Int,MouseMotionType]): Boolean = eventType == buttonCode
  def bindingDevice(): InputDeviceName = MouseDevice.NAME

  def bindRecognizer(recognizer: MouseButtonActionRecognizer): MouseButtonActionTrigger = {
    recognizers.add(recognizer)
    this
  }

  def pressed(x: Int, y: Int, weight: Long): Unit = {
    for(recognizer: MouseButtonActionRecognizer <- recognizers) recognizer.press(weight, x, y)
  }

  def released(x: Int, y: Int, weight: Long): Unit = {
    for(recognizer: MouseButtonActionRecognizer <- recognizers) recognizer.release(weight, x, y)
  }
}