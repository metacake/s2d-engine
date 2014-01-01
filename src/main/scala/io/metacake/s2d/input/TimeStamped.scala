package io.metacake.s2d.input

trait TimeStamped {
  var time: Long = _

  def getTimeStamp: Long = time
  def setTimeStamp(nTime: Long): Unit = this.time = nTime
}