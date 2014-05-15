package io.metacake.s2d.output.drawing.instructions

import java.awt.{Rectangle, Graphics2D, Color, Shape}
import java.awt.geom.Ellipse2D

class FilledShapeInstruction(val shape: Shape, color: Color) extends ColoredDrawInstruction(color) {
  override def render(context: Graphics2D): Unit = {
    super.render(context)
    context.fill(shape)
  }
}

class CircleInstruction(radius: Int, color: Color)
  extends FilledShapeInstruction(new Ellipse2D.Float(0, 0, radius, radius), color)

class RectangleInstruction(width: Int, height: Int, color: Color)
  extends FilledShapeInstruction(new Rectangle(0, 0, width, height), color)

class SquareInstruction(size: Int, color: Color)
  extends FilledShapeInstruction(new Rectangle(0, 0, size, size), color)