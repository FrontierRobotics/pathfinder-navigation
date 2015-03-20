package net.coro.guidance.api.route

import net.coro.guidance.api.data.gps.GPRMC
import net.coro.guidance.api.resource.GuidanceResource
import spray.routing.HttpService

trait GuidanceApi extends HttpService with GuidanceResource {
  val guidanceRoute = {
    import net.coro.guidance.api.protocol.GPSProtocol._

    path("gps" / "gprmc") {
      post {
        entity(as[GPRMC])(data => complete(consumeGPRMC(data)))
      }
    }
  }
}
