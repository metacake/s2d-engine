package io.metacake.s2d.window

import io.metacake.core.common.window.CakeWindow
import javax.swing.JFrame

class GraphicsWindow extends CakeWindow[JFrame] {
  val frame : JFrame = new JFrame()

  def dispose(): Unit = frame.dispose()

  def getX: Int = frame.getX

  def getY: Int = frame.getY

  def getWidth: Int = frame.getWidth

  def getHeight: Int = frame.getHeight

  def getRawWindow: JFrame = frame
}