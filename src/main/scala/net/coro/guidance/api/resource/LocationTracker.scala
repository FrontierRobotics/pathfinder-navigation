package net.coro.guidance.api.resource

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
