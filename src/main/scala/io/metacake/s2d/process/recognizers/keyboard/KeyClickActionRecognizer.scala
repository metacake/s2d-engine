package io.metacake.s2d.process.recognizers.keyboard

import io.metacake.core.process.ActionRecognizerName
import io.metacake.s2d.process.recognizers.ClickActionRecognizer

class KeyClickActionRecognizer(name: ActionRecognizerName) extends ClickActionRecognizer(name) with KeyActionRecognizer {

  override def press(timeStamp: Long): Unit = {
    if (!triggered) {
      triggered = true
      amount += 1
    }
  }

  override def release(timeStamp: Long): Unit = triggered = false
}