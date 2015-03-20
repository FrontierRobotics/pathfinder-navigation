package net.coro.guidance.api.data.gps

import java.time.LocalTime

import net.coro.guidance.api.data.Location

case class GPRMC(time: LocalTime, fixAcquired: Boolean, location: Location)

object GPRMC {
  def fromSentence(sentence: String): GPRMC = {
    val parts = sentence.split(",")
    val time = LocalTime.now()
    val fixAcquired = parts(2) == "A"
    val location = new Location

    new GPRMC(time, fixAcquired, location)
  }
}
