package io.metacake.s2d.process.recognizers.mouse

import io.metacake.core.process.ActionRecognizer


trait MouseButtonActionRecognizer extends ActionRecognizer {

  def press(weigth: Long, x: Int, y: Int): Unit

  def release(weigth: Long, x: Int, y: Int): Unit
}