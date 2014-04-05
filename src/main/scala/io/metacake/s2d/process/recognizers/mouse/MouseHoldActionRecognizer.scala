package io.metacake.s2d.process.recognizers.mouse

import io.metacake.core.process.ActionRecognizerName
import io.metacake.s2d.process.recognizers.HoldActionRecognizer

class MouseHoldActionRecognizer(name: ActionRecognizerName) extends HoldActionRecognizer(name) with MouseActionRecognizer {
  var weight: Long = _
  def press(weight: Long, x: Int, y: Int): Unit = {
    triggered = true
    this.weight = weight
  }

  def release(weight: Long, x: Int, y: Int): Unit = {
    triggered = false
    this.weight = 0
  }

  override def triggerWeight(): Int = weight.toInt
}