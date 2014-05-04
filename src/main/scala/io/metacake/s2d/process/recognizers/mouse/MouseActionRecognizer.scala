package io.metacake.s2d.process.recognizers.mouse

import io.metacake.core.process.ActionRecognizer


trait MouseActionRecognizer extends ActionRecognizer {

  private var x: Int = 0
  private var y: Int = 0

  def getX: Int = x
  def getY: Int = y

  def press(weight: Long, x: Int, y: Int): Unit = {
    this.x = x
    this.y = y
  }

  def release(weight: Long, x: Int, y: Int): Unit = {
    this.x = x
    this.y = y
  }
}