package io.metacake.s2d.process.recognizers

import io.metacake.core.process.{ActionRecognizerName, ActionRecognizer}

abstract class ReleaseActionRecognizer(name: ActionRecognizerName) extends ActionRecognizer {
  var triggered: Boolean = _

  def getName: ActionRecognizerName = name

  def wasTriggered(): Boolean = triggered

  def triggerWeight(): Int = {
    val isTriggered: Boolean = triggered
    this.triggered = false
    if (isTriggered) 1 else 0
  }

  def forgetActions(): Unit = this.triggered = false
}