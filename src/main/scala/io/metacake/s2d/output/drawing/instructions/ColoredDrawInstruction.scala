package io.metacake.s2d.output.drawing.instructions

import io.metacake.s2d.output.drawing.DrawInstruction
import java.awt.{Graphics2D, Color}

trait ColoredDrawInstruction extends DrawInstruction {
  val color: Color

  def render(context: Graphics2D) = context.setColor(color)
}