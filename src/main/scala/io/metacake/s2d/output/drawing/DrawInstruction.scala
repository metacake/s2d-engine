package io.metacake.s2d.output.drawing

import io.metacake.core.output.RenderingInstruction
import java.awt.Graphics2D

abstract class DrawInstruction(x: Int, y: Int) extends RenderingInstruction[Graphics2D] {
  def getX: Int = x

  def getY: Int = y
}