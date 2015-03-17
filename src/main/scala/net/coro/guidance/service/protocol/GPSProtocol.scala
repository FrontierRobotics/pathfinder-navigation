package net.coro.guidance.service.protocol

import net.coro.guidance.service.data.gps.GPRMC
import spray.http.{HttpEntity, MediaTypes}
import spray.httpx.marshalling.Marshaller
import spray.httpx.unmarshalling.Unmarshaller

object GPSProtocol {
  implicit val GPRMCMarshaller =
    Marshaller.of[GPRMC](MediaTypes.`text/plain`) { (value, contentType, ctx) =>
      ctx.marshalTo(HttpEntity(contentType, value.toSentence))
    }
  implicit val GPRMCUnmarshaller =
    Unmarshaller[GPRMC](MediaTypes.`text/plain`) {
      case HttpEntity.NonEmpty(contentType, data) => GPRMC.fromSentence(data.asString)
    }
}
