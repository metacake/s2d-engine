package io.metacake.s2d.process.recognizers.keyboard

import io.metacake.core.process.ActionRecognizerName
import io.metacake.s2d.process.recognizers.ReleaseActionRecognizer

class KeyReleaseActionRecognizer(name: ActionRecognizerName) extends ReleaseActionRecognizer(name) with KeyActionRecognizer {
  def press(timeStamp: Long): Unit = ()

  def release(timeStamp: Long): Unit = triggered = true
}