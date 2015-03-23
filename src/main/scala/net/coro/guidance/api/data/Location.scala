package net.coro.guidance.api.data

case class Location(latitude: Angle, longitude: Angle) {
  def toGmaps: String = {
    s"${latitude.toDecimal},${longitude.toDecimal}"
  }
}
