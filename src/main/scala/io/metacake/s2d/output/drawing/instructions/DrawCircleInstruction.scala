package io.metacake.s2d.output.drawing.instructions

import io.metacake.s2d.output.drawing.DrawInstruction
import java.awt.{Color, Graphics2D}

class DrawCircleInstruction(x: Int, y: Int, radius: Int, val color: Color) extends DrawInstruction(x, y) with ColoredDrawInstruction {

  override def render(context: Graphics2D): Unit = {
    super.render(context)
    val diameter: Int = 2 * radius
    context.fillOval(x - radius, y - radius, diameter, diameter)
  }
}