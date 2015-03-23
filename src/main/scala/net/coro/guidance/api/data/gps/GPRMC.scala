package net.coro.guidance.api.data.gps

import java.time.{LocalDateTime, LocalTime}

import net.coro.guidance.api.data._

case class GPRMC(dateTime: LocalDateTime, fixAcquired: Boolean, location: Location)

object GPRMC {
  def fromSentence(sentence: String): GPRMC = {
    val parts = sentence.split(",")
    val dateTime = dateTimeFromSentence(parts(1), parts(9))
    val fixAcquired = parts(2) == "A"
    val latitude = angleFromSentence(parts(3), parts(4), 2)
    val longitude = angleFromSentence(parts(5), parts(6), 3)

    GPRMC(dateTime, fixAcquired, Location(latitude, longitude))
  }

  private def dateTimeFromSentence(time: String, date: String) = {
    LocalDateTime.now()
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
