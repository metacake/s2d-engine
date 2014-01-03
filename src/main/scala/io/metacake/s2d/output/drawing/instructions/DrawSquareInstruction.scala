package io.metacake.s2d.output.drawing.instructions

import java.awt.Color

class DrawSquareInstruction(x: Int, y: Int, size: Int, color: Color) extends DrawRectangleInstruction(x, y, size, size, color)