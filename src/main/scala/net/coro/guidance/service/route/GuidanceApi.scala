package net.coro.guidance.service.route

import net.coro.guidance.service.data.gps.GPRMC
import spray.routing.HttpService

trait GuidanceApi extends HttpService {
  val guidanceRoute = {
    import net.coro.guidance.service.protocol.GPSProtocol._

    path("hello") {
      post {
        entity(as[GPRMC])(data => complete(consumeGPRMC(data)))
      }
    }
  }

  def consumeGPRMC(gprmc: GPRMC): String = {
    println(gprmc.toString)

    "Data Received"
  }
}
