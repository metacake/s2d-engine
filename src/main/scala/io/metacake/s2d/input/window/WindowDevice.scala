package io.metacake.s2d.input.window

import java.awt.Dimension
import java.awt.event.{ComponentAdapter, ComponentEvent}
import java.util
import javax.swing.JFrame

import io.metacake.core.common.window.CakeWindow
import io.metacake.core.input.system.InputDevice
import io.metacake.core.input.{ActionTrigger, InputDeviceName}
import io.metacake.core.process.RecognizerBucketName

import io.metacake.s2d.process.recognizers.window.WindowResizeRecognizer

import scala.collection.JavaConversions._

object WindowDevice {
  val Name: InputDeviceName = new InputDeviceName("S2D Window")
  val Key: RecognizerBucketName[WindowResizeRecognizer] = new RecognizerBucketName[WindowResizeRecognizer]()
  val Recognizer: WindowResizeRecognizer = new WindowResizeRecognizer
}

class WindowDevice extends InputDevice {
  var window: JFrame = _
  val triggers: util.Collection[WindowActionTrigger] = new util.ArrayList[WindowActionTrigger]

  override def bind(w: CakeWindow[_]): Unit = {
    window = w.getRawWindow.asInstanceOf[JFrame]
    window.addComponentListener(new ComponentAdapter {
      override def componentResized(e: ComponentEvent): Unit = {
        val point: Dimension = window.getSize
        for(t: WindowActionTrigger <- triggers) {
          if (t.isTriggeredBy(point)) {
            t.resize(point)
          }
        }
      }
    })
  }

  override def addTrigger(trigger: ActionTrigger[_]): Unit = {
    trigger match {
      case t : WindowActionTrigger => triggers.add(t)
      case _ => throw new ClassCastException
    }
  }

  override def name(): InputDeviceName = WindowDevice.Name

  override def releaseTriggers(): Unit = triggers.clear()

  override def shutdown(): Unit = ()

  override def startInputLoop(): Unit = ()
}