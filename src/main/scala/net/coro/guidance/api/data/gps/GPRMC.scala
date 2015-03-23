package net.coro.guidance.api.data.gps

import java.time.{LocalDate, LocalDateTime, LocalTime}

import net.coro.guidance.api.data._

case class GPRMC(fixAcquired: Boolean, dateTime: LocalDateTime, location: Option[Location])

object GPRMC {
  def fromSentence(sentence: String): GPRMC = {
    val parts = sentence.split(",")
    val fixAcquired = parts(2) == "A"
    val dateTime = dateTimeFromSentence(date = parts(9), time = parts(1))


    val location = locationFromSentence(latitude = parts(3),
                                        latitudeDirection = parts(4),
                                        longitude = parts(5),
                                        longitudeDirection = parts(6))


    GPRMC(fixAcquired, dateTime, location)
  }

  private def dateTimeFromSentence(date: String, time: String): LocalDateTime = {
    dateFromSentence(date).atTime(timeFromSentence(time))
  }

  private def dateFromSentence(date: String): LocalDate = {
    val dayOfMonth = date.substring(0, 2).toInt
    val month = date.substring(2, 4).toInt
    val year = 2000 + date.substring(4, 6).toInt

    LocalDate.of(year, month, dayOfMonth)
  }

  private def timeFromSentence(time: String): LocalTime = {
    val hour = time.substring(0, 2).toInt
    val minute = time.substring(2, 4).toInt
    val seconds = time.substring(4, 6).toInt
    val milliseconds = time.substring(7, time.length).toInt

    LocalTime.of(hour, minute, seconds, milliseconds * 1000000)
  }

  private def locationFromSentence(latitude: String,
                                   latitudeDirection: String,
                                   longitude: String,
                                   longitudeDirection: String): Option[Location] = {
    if (latitude.isEmpty || latitudeDirection.isEmpty || longitude.isEmpty || longitudeDirection.isEmpty) {
      return None
    }

    val latitudeAngle = angleFromSentence(latitude, latitudeDirection, degreeSize = 2)
    val longitudeAngle = angleFromSentence(longitude, longitudeDirection, degreeSize = 3)

    Option(Location(latitudeAngle, longitudeAngle))
  }

  private def angleFromSentence(degreesAndMinutes: String, direction: String, degreeSize: Int): Angle = {
    val degrees = degreesAndMinutes.substring(0, degreeSize).toInt
    val minutes = degreesAndMinutes.substring(degreeSize, degreesAndMinutes.length).toDouble

    direction match {
      case "N" => Angle(North, degrees, minutes)
      case "E" => Angle(East, degrees, minutes)
      case "S" => Angle(South, degrees, minutes)
      case "W" => Angle(West, degrees, minutes)
    }
  }
}
