package io.metacake.s2d.input.mouse

import io.metacake.core.input.{ActionTrigger, InputDeviceName}
import io.metacake.core.input.system.InputDevice
import java.awt.event.{MouseMotionListener, MouseEvent, MouseListener}
import io.metacake.core.common.window.CakeWindow
import java.util
import io.metacake.core.common.MilliTimer
import javax.swing.JFrame
import scala.collection.JavaConversions._

object MouseDevice {
  val NAME: InputDeviceName = new InputDeviceName("S2D Mouse")
}

class MouseDevice extends InputDevice with MouseMotionListener with MouseListener {

  val timer: MilliTimer = new MilliTimer()
  var triggers: util.Collection[MouseActionTrigger] = new util.ArrayList[MouseActionTrigger]()
  var (x: Int, y: Int) = (0, 0)

  // InputDevice
  def name(): InputDeviceName = MouseDevice.NAME

  def bind(window: CakeWindow[_]): Unit = {
    val frame: JFrame = window.asInstanceOf[CakeWindow[JFrame]].getRawWindow
    frame.addMouseListener(this)
    frame.addMouseMotionListener(this)
  }

  def addTrigger(trigger: ActionTrigger[_]): Unit = {
    trigger match {
      case t: MouseActionTrigger => triggers.add(t)
      case _ => throw new ClassCastException
    }
  }

  def releaseTriggers(): Unit = triggers = new util.ArrayList[MouseActionTrigger]()

  def startInputLoop(): Unit = ()

  def shutdown(): Unit = ()

  //MouseMotionListener
  def mouseMoved(e: MouseEvent): Unit = {
    val (dx, dy) = (e.getX - x, e.getY - y)
    x = e.getX
    y = e.getY
    if(dx != 0) handleMouseMoved(MouseMotionType.X, dx)
    if(dy != 0) handleMouseMoved(MouseMotionType.Y, dy)
  }

  def mouseDragged(e: MouseEvent): Unit = this.mouseMoved(e)

  private def handleMouseMoved(t: MouseMotionType, amount: Long) {
    for(trigger: MouseActionTrigger <- triggers) {
      if(trigger.isTriggeredBy(Right(t))) {
        //TODO: pressed is a little funny here, but it works for now
        trigger.pressed(x, y, amount)
      }
    }
  }

  // MouseListener
  def mousePressed(e: MouseEvent): Unit = mouseButtonHandler(e, (t: MouseActionTrigger,w: Long) => t.pressed(e.getX, e.getY, w))

  def mouseReleased(e: MouseEvent): Unit = mouseButtonHandler(e, (t: MouseActionTrigger,w: Long) => t.released(e.getX, e.getY, w))

  private def mouseButtonHandler(event: MouseEvent, func: (MouseActionTrigger, Long) => Unit): Unit = {
    val time : Long = timer.poll()
    for(trigger: MouseActionTrigger <- triggers) {
      if (trigger.isTriggeredBy(Left(event.getButton))) {
        func.apply(trigger,time)
      }
    }
  }

 // junk
  def mouseClicked(e: MouseEvent): Unit = e.consume()

  def mouseEntered(e: MouseEvent): Unit = e.consume()

  def mouseExited(e: MouseEvent): Unit = e.consume()
}
