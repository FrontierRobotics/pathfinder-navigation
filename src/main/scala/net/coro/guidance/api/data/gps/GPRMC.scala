package net.coro.guidance.api.data.gps

import java.time.temporal.TemporalUnit
import java.time.{LocalDate, LocalDateTime, LocalTime}

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

  private def dateTimeFromSentence(time: String, date: String):LocalDateTime = {
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
