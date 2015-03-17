package net.coro.guidance.service.route

import net.coro.guidance.service.Main._
import net.coro.guidance.service.data.gps.GPRMC
import spray.routing.HttpService

class GuidanceServiceActor {

}

trait GuidanceApi extends HttpService {
  val guidanceRoute = {
    import net.coro.guidance.service.protocol.GPSProtocol._

    path("hello") {
      post {
        entity(as[GPRMC])(data => complete(consumeGPRMC(data)))
      }
    }
  }
}
