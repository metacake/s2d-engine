package io.metacake.s2d.output.drawing.instructions

import io.metacake.core.output.RenderingInstruction
import java.awt.{Color, Graphics2D}

abstract class DrawInstruction extends RenderingInstruction[Graphics2D]

abstract class ColoredDrawInstruction(color: Color) extends DrawInstruction {
  override def render(context: Graphics2D) = context.setColor(color)
}