package net.coro.guidance.api.resource

import net.coro.guidance.api.data.Location

import scala.collection.mutable.ArrayBuffer

class LocationTracker {
  private val locationHistory = new ArrayBuffer[Location]

  def updateCurrent(location: Location) = {
    location +=: locationHistory
  }

  def getCurrent: Option[Location] = {
    locationHistory.headOption
  }
}
