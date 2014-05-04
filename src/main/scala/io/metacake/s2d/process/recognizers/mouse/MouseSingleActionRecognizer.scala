package io.metacake.s2d.process.recognizers.mouse

import io.metacake.s2d.process.recognizers.HoldActionRecognizer
import io.metacake.core.process.ActionRecognizerName

class MouseSingleActionRecognizer(name: ActionRecognizerName) extends HoldActionRecognizer(name) with MouseActionRecognizer {
  var weight: Long = _
  override def press(weight: Long, x: Int, y: Int): Unit = {
    super.press(weight, x, y)
    triggered = true
    this.weight = weight
  }

  override def wasTriggered(): Boolean = {
    val actuallyTriggered = triggered
    triggered = false
    actuallyTriggered
  }

  override def triggerWeight(): Int = weight.toInt
}