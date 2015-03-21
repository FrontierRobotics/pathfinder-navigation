package net.coro.guidance.api.data

sealed trait Direction {
  def name: String

  def reverseAngle: Boolean = false
}

case object North extends Direction {val name = "North"}

case object South extends Direction {val name = "South"; override val reverseAngle = true}

case object East extends Direction {val name = "East"}

case object West extends Direction {val name = "West"; override val reverseAngle = true}

case class Angle(direction: Direction, degrees: Int, minutes: Double) {
  def toDecimal: Double = {
    val decimalMinutes = minutes / 60
    val decimalDegrees = degrees + decimalMinutes
    if (direction.reverseAngle) 0 - decimalDegrees else decimalDegrees
  }
}

case class Location(latitude: Angle, longitude: Angle) {
  def toGmaps: String = {
    s"${latitude.toDecimal},${longitude.toDecimal}"
  }
}
