package io.metacake.s2d.output

import java.util
import io.metacake.core.output.{OutputDeviceName, RenderingInstruction}
import java.util.Collections
import io.metacake.core.output.system.OutputDevice
import io.metacake.core.common.window.CakeWindow
import io.metacake.core.common.TimedLoopThread
import javax.swing.JFrame
import java.awt.image.BufferStrategy
import io.metacake.s2d.window.GraphicsWindow
import java.awt.{Color, Insets, Graphics2D}
import scala.collection.JavaConversions._

object DrawingDevice {
  val NAME : OutputDeviceName = new OutputDeviceName()
  val BUFFERS : Int = 2
}

class DrawingDevice extends OutputDevice {

  var frame : JFrame = null
  var strategy : BufferStrategy = null
  val drawingThread : TimedLoopThread = new TimedLoopThread(new Runnable {
    def run(): Unit = draw(instructions.asInstanceOf[util.Collection[RenderingInstruction[Graphics2D]]])
  })
  var instructions : util.Collection[RenderingInstruction[_]] = Collections.emptyList()

  def name(): OutputDeviceName = DrawingDevice.NAME

  def render(instructions: util.Collection[RenderingInstruction[_]]): Unit = {
    this.instructions = instructions
  }

  def startOutputLoop(): Unit = drawingThread.start()

  def shutdown(): Unit = drawingThread.requestStop()

  def bind(cakeWindow: CakeWindow[_]): Unit = {
    cakeWindow match {
      case gWindow: GraphicsWindow =>
        frame = gWindow.getRawWindow()
        frame.createBufferStrategy(DrawingDevice.BUFFERS)
        strategy = frame.getBufferStrategy
      case _ => throw new ClassCastException
    }

  }

  def draw(instructions : util.Collection[RenderingInstruction[Graphics2D]]): Unit = {
    val parentGraphics: Graphics2D = strategy.getDrawGraphics.asInstanceOf[Graphics2D]
    val insets: Insets = frame.getInsets
    parentGraphics.translate(insets.right, insets.top)
    parentGraphics.setColor(Color.WHITE)
    parentGraphics.fillRect(0, 0, frame.getWidth, frame.getHeight)
    for (instruction: RenderingInstruction[Graphics2D] <- instructions) {
      val graphics = parentGraphics.create().asInstanceOf[Graphics2D]
      instruction.render(graphics)
      graphics.dispose()
    }
  }
}