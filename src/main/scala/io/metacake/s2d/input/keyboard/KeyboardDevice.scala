package io.metacake.s2d.input.keyboard

import io.metacake.core.input.system.InputDevice
import java.awt.event.{KeyEvent, KeyListener}
import io.metacake.core.input.{InputDeviceName, ActionTrigger}
import io.metacake.core.common.window.CakeWindow
import java.util
import io.metacake.core.common.MilliTimer
import javax.swing.JFrame
import scala.collection.JavaConversions._

object KeyboardDevice {
  val NAME: InputDeviceName = new InputDeviceName()
}

class KeyboardDevice extends InputDevice with KeyListener {

  var triggers : util.Collection[KeyboardActionTrigger] = new util.ArrayList[KeyboardActionTrigger]()
  val timer : MilliTimer = new MilliTimer()

  def keyPressed(event: KeyEvent): Unit = keyHandler(event, (trigger: KeyboardActionTrigger) => trigger.pressed())

  def keyReleased(event: KeyEvent): Unit = keyHandler(event, (trigger: KeyboardActionTrigger) => trigger.released())

  private def keyHandler(event: KeyEvent, func: KeyboardActionTrigger => Unit): Unit = {
    val time : Long = timer.poll()
    for(trigger: KeyboardActionTrigger <- triggers) {
      if (trigger.isTriggeredBy(event)) {
        trigger setTimeStamp time
        func apply trigger
      }
    }
  }

  def name(): InputDeviceName = KeyboardDevice.NAME

  def bind(window: CakeWindow[_]): Unit = window.asInstanceOf[CakeWindow[JFrame]].getRawWindow.addKeyListener(this)

  def addTrigger(trigger: ActionTrigger[_]): Unit = {
    trigger match {
      case t : KeyboardActionTrigger => triggers.add(t)
      case _ => throw new ClassCastException
    }
  }

  def releaseTriggers(): Unit = triggers = new util.ArrayList[KeyboardActionTrigger]()

  def startInputLoop(): Unit = ()

  def shutdown(): Unit = ()

  def keyTyped(e: KeyEvent): Unit = ()
}