package io.metacake.s2d.window

import io.metacake.core.common.window.CakeWindow
import javax.swing.JFrame
import java.awt.Insets
import java.awt.event.{WindowEvent, WindowAdapter}

class GraphicsWindow(private val width: Int, private val height: Int) extends CakeWindow[JFrame] {
  val frame : JFrame = new JFrame()
  frame setVisible true
  val insets : Insets = frame.getInsets
  frame setSize(width + insets.left + insets.right, height + insets.top + insets.bottom)
  frame addWindowListener new WindowAdapter {
    override def windowClosing(event: WindowEvent): Unit = close()
  }

  def dispose(): Unit = frame.dispose()

  def getX: Int = frame.getX

  def getY: Int = frame.getY

  def getWidth: Int = frame.getWidth

  def getHeight: Int = frame.getHeight

  def getRawWindow: JFrame = frame
}