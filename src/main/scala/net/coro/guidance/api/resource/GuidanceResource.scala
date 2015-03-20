package net.coro.guidance.api.resource

import net.coro.guidance.api.data.gps.GPRMC

trait GuidanceResource {
  val locationTracker: LocationTracker

  def consumeGPRMC(gprmc: GPRMC): String = {
    println(gprmc.toString)
    locationTracker.updateCurrent(gprmc.location)

    "Data Received"
  }
}
