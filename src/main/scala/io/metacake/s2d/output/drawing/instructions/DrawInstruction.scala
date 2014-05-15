package io.metacake.s2d.output.drawing.instructions

import io.metacake.core.output.RenderingInstruction
import java.awt.{Rectangle, Shape, Color, Graphics2D}
import java.awt.geom.Ellipse2D

abstract class DrawInstruction extends RenderingInstruction[Graphics2D]

class PlaceInstruction(image: DrawInstruction, x: Int, y: Int, base: DrawInstruction) extends DrawInstruction {
  override def render(context: Graphics2D): Unit = {
    base.render(context)
    context.translate(x, y)
    image.render(context)
    context.translate(-x, -y)
  }
}

abstract class ColoredDrawInstruction(color: Color) extends DrawInstruction {
  override def render(context: Graphics2D) = context.setColor(color)
}

class TextInstruction(text: String, x: Int, y: Int, color: Color) extends ColoredDrawInstruction(color) {
  override def render(context: Graphics2D): Unit = {
    super.render(context)
    context.drawString(text, x, y)
  }
}

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