package net.coro.guidance.api.data

import net.coro.TestSpec

class LocationTest extends TestSpec {
  "The location must convert to Google Maps" in {
    val location = Location(Angle(North, 41, 10.1833), Angle(West, 104, 49.5843))

    location.toGmaps mustBe "41.16972166670693,-104.826404999627"
  }
}
