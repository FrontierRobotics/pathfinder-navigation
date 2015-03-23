package net.coro.guidance.api.data.gps

import java.time.LocalTime

import net.coro.guidance.api.data._

case class GPRMC(time: LocalTime, fixAcquired: Boolean, location: Location)

object GPRMC {
  def fromSentence(sentence: String): GPRMC = {
    val parts = sentence.split(",")
    val time = LocalTime.now()
    val fixAcquired = parts(2) == "A"
    val latitude = angleFromSentence(parts(3), parts(4), 2)
    val longitude = angleFromSentence(parts(5), parts(6), 3)
    val location = new Location(latitude, longitude)

    new GPRMC(time, fixAcquired, location)
  }

  private def angleFromSentence(angle: String, direction: String, degreeSize: Int): Angle = {
    val degrees = angle.substring(0, degreeSize).toInt
    val minutes = angle.substring(degreeSize, angle.length).toDouble

    direction match {
      case "N" => Angle(North, degrees, minutes)
      case "E" => Angle(East, degrees, minutes)
      case "S" => Angle(South, degrees, minutes)
      case "W" => Angle(West, degrees, minutes)
    }
  }
}
