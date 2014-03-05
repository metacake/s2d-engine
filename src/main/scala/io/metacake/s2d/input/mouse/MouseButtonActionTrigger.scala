package io.metacake.s2d.input.mouse

import io.metacake.core.input.{InputDeviceName, ActionTrigger}
import java.awt.event.MouseEvent
import io.metacake.s2d.input.TimeStamped
import java.util
import io.metacake.s2d.process.recognizers.mouse.MouseButtonActionRecognizer
import scala.collection.JavaConversions._

class MouseButtonActionTrigger(private val buttonCode: Either[Int,MouseMotionType]) extends ActionTrigger[MouseEvent] with TimeStamped {

  val recognizers: util.Collection[MouseButtonActionRecognizer] = new util.ArrayList[MouseButtonActionRecognizer]()

  def isTriggeredBy(eventType: Either[Int,MouseMotionType]): Boolean = event.getButton == buttonCode
  def bindingDevice(): InputDeviceName = MouseDevice.NAME

  def bindRecognizer(recognizer: MouseButtonActionRecognizer): MouseButtonActionTrigger = {
    recognizers.add(recognizer)
    this
  }

  def pressed(x: Int, y: Int): Unit = {
    for(recognizer: MouseButtonActionRecognizer <- recognizers) recognizer.press(this.getTimeStamp, x, y)
  }

  def released(x: Int, y: Int): Unit = {
    for(recognizer: MouseButtonActionRecognizer <- recognizers) recognizer.release(this.getTimeStamp, x, y)
  }
}