package net.coro.guidance.api.route

import net.coro.guidance.api.data.gps.GPRMC
import spray.routing.HttpService

trait GuidanceApi extends HttpService {
  val guidanceRoute = {
    import net.coro.guidance.api.protocol.GPSProtocol._

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
