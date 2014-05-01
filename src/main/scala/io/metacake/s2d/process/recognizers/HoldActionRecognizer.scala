package io.metacake.s2d.process.recognizers

import io.metacake.core.process.{ActionRecognizerName, ActionRecognizer}

abstract class HoldActionRecognizer(name: ActionRecognizerName) extends ActionRecognizer {
  var triggered: Boolean = _

  def getName: ActionRecognizerName = name

  def wasTriggered(): Boolean = triggered

  def triggerWeight(): Int = if (triggered) 1 else 0

  def forgetActions(): Unit = triggered = false
}