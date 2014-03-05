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
  val NAME: InputDeviceName = new InputDeviceName("S2D Keyboard")
}

class KeyboardDevice extends InputDevice with KeyListener {

  var triggers : util.Collection[KeyboardActionTrigger] = new util.ArrayList[KeyboardActionTrigger]()
  val timer : MilliTimer = new MilliTimer()

  def keyPressed(event: KeyEvent): Unit = keyHandler(event, (tr: KeyboardActionTrigger, time:Long) => tr.pressed(time))

  def keyReleased(event: KeyEvent): Unit = keyHandler(event, (tr: KeyboardActionTrigger, time:Long) => tr.released(time))

  private def keyHandler(event: KeyEvent, func: (KeyboardActionTrigger, Long) => Unit): Unit = {
    val time : Long = timer.poll()
    for(trigger: KeyboardActionTrigger <- triggers) {
      if (trigger.isTriggeredBy(event)) {
        func.apply(trigger,time)
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