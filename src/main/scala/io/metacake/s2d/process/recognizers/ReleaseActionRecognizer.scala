package io.metacake.s2d.process.recognizers

import io.metacake.core.process.{ActionRecognizerName, ActionRecognizer}

class ReleaseActionRecognizer(name: ActionRecognizerName) extends ActionRecognizer {
  var triggered: Boolean = _

  def getName: ActionRecognizerName = name

  def wasTriggered(): Boolean = {
    val isTriggered: Boolean = triggered
    this.triggered = false
    isTriggered
  }

  def triggerWeight(): Int = 1

  def forgetActions(): Unit = this.triggered = false
}