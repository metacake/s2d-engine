package io.metacake.s2d.process.recognizers.mouse

import io.metacake.core.process.ActionRecognizerName
import io.metacake.s2d.process.recognizers.ReleaseActionRecognizer

class MouseReleaseActionRecognizer(name: ActionRecognizerName) extends ReleaseActionRecognizer(name) with MouseActionRecognizer {

  override def release(timeStamp: Long, x: Int, y: Int): Unit = {
    super.release(timeStamp, x, y)
    triggered = true
  }
}