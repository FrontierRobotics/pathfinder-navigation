package net.coro.guidance.api.data.gps

import java.time.{LocalDate, LocalDateTime, LocalTime}

import net.coro.guidance.api.data._

case class GPRMC(fixAcquired: Boolean, dateTime: LocalDateTime, location: Location)

object GPRMC {
  def fromSentence(sentence: String): GPRMC = {
    val parts = sentence.split(",")
    val fixAcquired = parts(2) == "A"
    val dateTime = dateTimeFromSentence(date = parts(9), time = parts(1))
    val latitude = angleFromSentence(angle = parts(3), direction = parts(4), degreeSize = 2)
    val longitude = angleFromSentence(angle = parts(5), direction = parts(6), degreeSize = 3)

    GPRMC(fixAcquired, dateTime, Location(latitude, longitude))
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
