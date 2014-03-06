package io.metacake.s2d.process.recognizers.mouse

import io.metacake.core.process.ActionRecognizer


trait MouseActionRecognizer extends ActionRecognizer {

  def press(weight: Long, x: Int, y: Int): Unit

  def release(weight: Long, x: Int, y: Int): Unit
}