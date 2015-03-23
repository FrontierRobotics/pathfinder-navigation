package net.coro.guidance.api.data

case class Angle(direction: Direction, degrees: Int, minutes: Int, seconds: Float) {
  def toDecimal: Double = {
    val decimalSeconds = (seconds.toDouble / 60) / 60
    val decimalMinutes = minutes.toDouble / 60
    val decimalDegrees = degrees.toDouble + decimalMinutes + decimalSeconds
    if (direction.reverseAngle) 0 - decimalDegrees else decimalDegrees
  }
}

object Angle {
  def apply(direction: Direction, degrees: Double): Angle = {
    val wholeDegrees = degrees.toInt
    val minutes = (degrees - wholeDegrees) * 60
    Angle(direction, wholeDegrees, minutes)
  }

  def apply(direction: Direction, degrees: Int, minutes: Double): Angle = {
    val wholeMinutes = minutes.toInt
    val seconds = (minutes - wholeMinutes) * 60
    Angle(direction, degrees, wholeMinutes, seconds.toFloat)
  }
}
