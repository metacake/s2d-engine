package io.metacake.s2d.output.drawing.instructions

import io.metacake.s2d.output.drawing.DrawInstruction
import java.awt.Graphics2D

class DrawCircleInstruction(x: Int, y: Int, radius: Int) extends DrawInstruction(x, y) {
  def render(context: Graphics2D): Unit = context.fillOval(x, y, radius, radius)
}
