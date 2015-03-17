package net.coro.guidance.service

import akka.actor.ActorSystem
import net.coro.guidance.service.data.gps.GPRMC
import spray.routing.SimpleRoutingApp

object Main extends App with SimpleRoutingApp {
  implicit val system = ActorSystem("guidance-service")


  startServer(interface = "192.168.1.7", port = 8080) {
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
