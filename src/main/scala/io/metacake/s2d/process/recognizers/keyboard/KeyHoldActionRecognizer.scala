package io.metacake.s2d.process.recognizers.keyboard

import io.metacake.s2d.process.recognizers.HoldActionRecognizer
import io.metacake.core.process.ActionRecognizerName

class KeyHoldActionRecognizer(name: ActionRecognizerName) extends HoldActionRecognizer(name) with KeyActionRecognizer {
  def press(timeStamp: Long): Unit = triggered = true

  def release(timeStamp: Long): Unit = triggered = false
}