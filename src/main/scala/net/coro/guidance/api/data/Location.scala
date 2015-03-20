package net.coro.guidance.api.data

case class Angle(degrees: Int, minutes: Double) {
  def toDecimal: Double = {
    val decimalMinutes = minutes / 60
    if (degrees < 0) degrees - decimalMinutes else degrees + decimalMinutes
  }
}

case class Location(latitude: Angle, longitude: Angle) {
  def toGmaps: String = {
    s"${latitude.toDecimal},${longitude.toDecimal}"
  }
}
