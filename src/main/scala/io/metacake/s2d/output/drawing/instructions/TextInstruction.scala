package io.metacake.s2d.output.drawing.instructions

import java.awt.{FontMetrics, Font, Graphics2D, Color}

class TextInstruction(text: String, font: Font, color: Color) extends ColoredDrawInstruction(color) {
  override def render(context: Graphics2D): Unit = {
    super.render(context)
    context.setFont(font)
    context.drawString(text, 0, 0)
  }
}