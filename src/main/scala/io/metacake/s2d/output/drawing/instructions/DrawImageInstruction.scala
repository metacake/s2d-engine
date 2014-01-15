package io.metacake.s2d.output.drawing.instructions

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import io.metacake.s2d.output.drawing.DrawInstruction

class DrawImageInstruction(image: BufferedImage, x: Int, y: Int) extends DrawInstruction(x, y) {

  override def render(context: Graphics2D): Unit = {
    context.drawImage(image, x, y, null)
  }
}