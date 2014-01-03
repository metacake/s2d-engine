package io.metacake.s2d.output.drawing.instructions

import io.metacake.s2d.output.drawing.DrawInstruction
import java.awt.{Color, Graphics2D}

class DrawStringInstruction(text: String, x: Int, y: Int, val color: Color) extends DrawInstruction(x, y) with ColoredDrawInstruction {
  override def render(context: Graphics2D): Unit = {
    super.render(context)
    context.drawString(text, x, y)
  }
}