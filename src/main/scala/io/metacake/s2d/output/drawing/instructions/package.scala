package io.metacake.s2d.output.drawing

import java.awt.{Shape, Rectangle, Color, Graphics2D}
import io.metacake.core.output.RenderingInstruction
import java.awt.geom.Ellipse2D

package object instructions {
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

  class FilledShapeInstruction(val shape: Shape, color: Color) extends ColoredDrawInstruction(color) {
    override def render(context: Graphics2D): Unit = {
      super.render(context)
      context.fill(shape)
    }
  }

  class RectangleInstruction(width: Int, height: Int, color: Color)
    extends FilledShapeInstruction(new Rectangle(0, 0, width, height), color)
  class CircleInstruction(radius: Int, color: Color)
    extends FilledShapeInstruction(new Ellipse2D.Float(0, 0, radius, radius), color)
}