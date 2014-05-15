package io.metacake.s2d.output.drawing.instructions

import java.awt.Graphics2D

class PlaceInstruction(image: DrawInstruction, x: Int, y: Int, base: DrawInstruction) extends DrawInstruction {
  override def render(context: Graphics2D): Unit = {
    base.render(context)
    context.translate(x, y)
    image.render(context)
    context.translate(-x, -y)
  }
}