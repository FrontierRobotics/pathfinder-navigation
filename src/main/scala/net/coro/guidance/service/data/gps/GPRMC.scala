package net.coro.guidance.service.data.gps

import java.time.LocalTime

case class GPRMC(time: LocalTime, fixAquired: Boolean, latitude: String, longitude: String) {
  def toSentence = {
    "howdy"
  }
}

object GPRMC {
  def fromSentence(sentence: String): GPRMC = {
    new GPRMC(LocalTime.now(), true, sentence, "blah")
  }
}
