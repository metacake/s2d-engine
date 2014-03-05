package io.metacake.s2d.input.mouse

import io.metacake.core.common.Symbol


object MouseMotionType {
  val X = new MouseMotionType("X")
  val Y = new MouseMotionType("Y")
}

class MouseMotionType private (name: String) extends Symbol(name)