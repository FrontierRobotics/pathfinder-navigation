package net.coro.guidance.api

import akka.actor.{Actor, ActorSystem, Props}
import akka.io.IO
import net.coro.guidance.api.resource.LocationTracker
import net.coro.guidance.api.route.GuidanceApi
import spray.can.Http

object Main extends App {
  implicit val system = ActorSystem("guidance-service")

  val locationTracker = new LocationTracker()
  val service   = system.actorOf(Props(classOf[GuidanceServiceActor], locationTracker), "GuidanceService")
  val interface = "192.168.1.7"
  val port      = 8080

  IO(Http) ! Http.Bind(service, interface, port)
}

class GuidanceServiceActor(override val locationTracker: LocationTracker) extends Actor with GuidanceApi {
  def actorRefFactory = context

  def receive = runRoute(guidanceRoute)
}
