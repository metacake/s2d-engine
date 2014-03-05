package io.metacake.s2d.input.mouse

import io.metacake.core.input.{ActionTrigger, InputDeviceName}
import io.metacake.core.input.system.InputDevice
import java.awt.event.{MouseEvent, MouseListener}
import io.metacake.core.common.window.CakeWindow
import java.util
import io.metacake.core.common.MilliTimer
import javax.swing.JFrame
import scala.collection.JavaConversions._

object MouseDevice {
  val NAME: InputDeviceName = new InputDeviceName()
}

class MouseDevice extends InputDevice with MouseListener {

  var triggers: util.Collection[MouseButtonActionTrigger] = new util.ArrayList[MouseButtonActionTrigger]()
  val timer: MilliTimer = new MilliTimer()

  def mousePressed(e: MouseEvent): Unit = mouseButtonHandler(e, (t: MouseButtonActionTrigger) => t.pressed(e.getX, e.getY))

  def mouseReleased(e: MouseEvent): Unit = mouseButtonHandler(e, (t: MouseButtonActionTrigger) => t.released(e.getX, e.getY))

  private def mouseButtonHandler(event: MouseEvent, func: MouseButtonActionTrigger => Unit): Unit = {
    val time : Long = timer.poll()
    for(trigger: MouseButtonActionTrigger <- triggers) {
      if (trigger.isTriggeredBy(event)) {
        trigger.setTimeStamp(time)
        func.apply(trigger)
      }
    }
  }

  def name(): InputDeviceName = MouseDevice.NAME

  def bind(window: CakeWindow[_]): Unit = window.asInstanceOf[CakeWindow[JFrame]].getRawWindow.addMouseListener(this)

  def addTrigger(trigger: ActionTrigger[_]): Unit = {
    trigger match {
      case t: MouseButtonActionTrigger => triggers.add(t)
      case _ => throw new ClassCastException
    }
  }

  def releaseTriggers(): Unit = triggers = new util.ArrayList[MouseButtonActionTrigger]()

  def mouseClicked(e: MouseEvent): Unit = e.consume()

  def mouseEntered(e: MouseEvent): Unit = e.consume()

  def mouseExited(e: MouseEvent): Unit = e.consume()

  def startInputLoop(): Unit = ()

  def shutdown(): Unit = ()
}