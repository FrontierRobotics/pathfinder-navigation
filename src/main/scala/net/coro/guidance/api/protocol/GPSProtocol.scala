package net.coro.guidance.api.protocol

import net.coro.guidance.api.data.gps.GPRMC
import spray.http.{HttpEntity, MediaTypes}
import spray.httpx.marshalling.Marshaller
import spray.httpx.unmarshalling.Unmarshaller

object GPSProtocol {
  implicit val GPRMCUnmarshaller =
    Unmarshaller[GPRMC](MediaTypes.`text/plain`) {
      case HttpEntity.NonEmpty(contentType, data) => GPRMC.fromSentence(data.asString)
    }
}
