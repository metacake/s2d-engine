package io.metacake.s2d.process.recognizers.mouse

import io.metacake.core.process.ActionRecognizerName
import io.metacake.s2d.process.recognizers.ReleaseActionRecognizer

class MouseReleaseActionRecognizer(name: ActionRecognizerName) extends ReleaseActionRecognizer(name) with MouseActionRecognizer {
  def press(timeStamp: Long, x: Int, y: Int): Unit = ()

  def release(timeStamp: Long, x: Int, y: Int): Unit = triggered = true
}