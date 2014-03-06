package io.metacake.s2d.process.recognizers.mouse

import io.metacake.core.process.ActionRecognizerName
import io.metacake.s2d.process.recognizers.HoldActionRecognizer

class MouseHoldActionRecognizer(name: ActionRecognizerName) extends HoldActionRecognizer(name) with MouseActionRecognizer {
  def press(timeStamp: Long, x: Int, y: Int): Unit = triggered = true

  def release(timeStamp: Long, x: Int, y: Int): Unit = triggered = false
}