package io.metacake.s2d.output.drawing.instructions

import io.metacake.s2d.output.drawing.DrawInstruction
import java.awt.{Color, Graphics2D}

class DrawRectangleInstruction(x: Int, y: Int, width: Int, height: Int, val color: Color) extends DrawInstruction(x, y) with ColoredDrawInstruction {

  override def render(context: Graphics2D): Unit = {
    super.render(context)
    context.fillRect(x - (width / 2), y - (height / 2), width, height)
  }
}