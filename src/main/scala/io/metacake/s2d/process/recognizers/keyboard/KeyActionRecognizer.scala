package io.metacake.s2d.process.recognizers.keyboard

import io.metacake.core.process.ActionRecognizer

trait KeyActionRecognizer extends ActionRecognizer {
  var triggered: Boolean

  def press(timeStamp: Long): Unit

  def release(timeStamp: Long): Unit
}