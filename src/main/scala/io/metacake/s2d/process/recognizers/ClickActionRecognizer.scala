package io.metacake.s2d.process.recognizers

import io.metacake.core.process.{ActionRecognizer, ActionRecognizerName}

class ClickActionRecognizer(name: ActionRecognizerName) extends ActionRecognizer {
  var triggered: Boolean = _
  var amount: Int = 0

  override def getName: ActionRecognizerName = name

  override def triggerWeight(): Int = {
    val weight: Int = amount
    amount = 0
    triggered = false
    weight
  }

  override def wasTriggered(): Boolean = triggered

  override def forgetActions(): Unit = triggered = false

  override def toString: String = "[Click Action: triggered - " + triggered + ", amount - " + amount + "]"
}