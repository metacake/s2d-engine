package io.metacake.s2d.process.recognizers

import io.metacake.core.process.{ActionRecognizerName, ActionRecognizer}

class HoldActionRecognizer(name: ActionRecognizerName) extends ActionRecognizer {
  var triggered: Boolean = _

  def getName: ActionRecognizerName = name

  def wasTriggered(): Boolean = triggered

  def triggerWeight(): Int = 1

  def forgetActions(): Unit = triggered = false
}