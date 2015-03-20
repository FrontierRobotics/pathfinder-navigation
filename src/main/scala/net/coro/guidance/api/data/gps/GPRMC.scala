package net.coro.guidance.api.data.gps

import java.time.LocalTime

import net.coro.guidance.api.data.{Angle, Location}

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

  def angleFromSentence(degreesAndMinutes: String, direction: String, degreeSize: Int): Angle = {
    val degrees = degreesAndMinutes.substring(0, degreeSize).toInt
    val minutes = degreesAndMinutes.substring(degreeSize, degreesAndMinutes.length).toDouble

    if (direction == "N" || direction == "E") {
      new Angle(degrees, minutes)
    }
    else {
      new Angle(0 - degrees, minutes)
    }
  }
}
