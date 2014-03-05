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


  var triggers: util.Collection[MouseButtonActionTrigger] = new util.ArrayList[MouseButtonActionTrigger]()
  val timer: MilliTimer = new MilliTimer()
  var x = 0
  var y = 0
  //MouseMotionListener
  def mouseMoved(e: MouseEvent): Unit = {
    val xMoved = e.getX - x
    val yMoved = e.getY - y
    this.x = e.getX
    this.y = e.getY
    if(xMoved != 0) handleMouseMoved(MouseMotionType.X,xMoved)
    if(yMoved != 0) handleMouseMoved(MouseMotionType.Y,yMoved)
  }

  def mouseDragged(e: MouseEvent): Unit = this.mouseMoved(e)

  private def handleMouseMoved(t: MouseMotionType, amount: Long) {
    for(trigger: MouseButtonActionTrigger <- triggers) {
      if(trigger.isTriggeredBy(Right(t))) {
        //TODO: pressed is a little funny here, but it works for now
        trigger.pressed(x,y,amount)
      }
    }
  }

  // MouseListener
  def mousePressed(e: MouseEvent): Unit = mouseButtonHandler(e, (t: MouseButtonActionTrigger,w: Long) => t.pressed(e.getX, e.getY, w))

  def mouseReleased(e: MouseEvent): Unit = mouseButtonHandler(e, (t: MouseButtonActionTrigger,w: Long) => t.released(e.getX, e.getY, w))

  private def mouseButtonHandler(event: MouseEvent, func: (MouseButtonActionTrigger, Long) => Unit): Unit = {
    val time : Long = timer.poll()
    for(trigger: MouseButtonActionTrigger <- triggers) {
      if (trigger.isTriggeredBy(Left(event.getButton))) {
        func.apply(trigger,time)
      }
    }
  }

  // InputDevice
  def name(): InputDeviceName = MouseDevice.NAME

  def bind(window: CakeWindow[_]): Unit = window.asInstanceOf[CakeWindow[JFrame]].getRawWindow.addMouseListener(this)

  def addTrigger(trigger: ActionTrigger[_]): Unit = {
    trigger match {
      case t: MouseButtonActionTrigger => triggers.add(t)
      case _ => throw new ClassCastException
    }
  }

  def releaseTriggers(): Unit = triggers = new util.ArrayList[MouseButtonActionTrigger]()

  def startInputLoop(): Unit = ()

  def shutdown(): Unit = ()


  // junk
  def mouseClicked(e: MouseEvent): Unit = e.consume()

  def mouseEntered(e: MouseEvent): Unit = e.consume()

  def mouseExited(e: MouseEvent): Unit = e.consume()
}
