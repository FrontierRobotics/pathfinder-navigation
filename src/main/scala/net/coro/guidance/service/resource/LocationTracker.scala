package net.coro.guidance.service.resource

import scala.collection.mutable.ArrayBuffer

class LocationTracker {
  private val locationHistory = new ArrayBuffer[Location]()

  def updateCurrent(location: Location) = {
    location +=: locationHistory
  }

  def getCurrent = {
    locationHistory.head
  }
}
