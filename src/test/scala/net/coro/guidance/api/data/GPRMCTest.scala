package net.coro.guidance.api.data

import java.time.LocalDateTime

import net.coro.TestSpec
import net.coro.guidance.api.data.gps.GPRMC

class GPRMCTest extends TestSpec {
  "The fix must not be acquired from a void GPRMC sentence" in {
    val gprmc = GPRMC.fromSentence("$GPRMC,215952.087,V,,,,,0.00,0.00,070180,,,N*44")

    gprmc.fixAcquired mustBe false
  }

  "The fix must be acquired from an active GPRMC sentence" in {
    val gprmc = GPRMC.fromSentence("$GPRMC,034444.000,A,4110.1833,N,10449.5843,W,1.50,296.10,200315,,,A*7D")

    gprmc.fixAcquired mustBe true
  }

  "The location must parse correctly from an active GPRMC sentence" in {
    val gprmc = GPRMC.fromSentence("$GPRMC,034444.000,A,4110.1833,N,10449.5843,W,1.50,296.10,200315,,,A*7D")
    val expectedLocation = Location(Angle(North, 41, 10.1833), Angle(West, 104, 49.5843))

    gprmc.location mustBe expectedLocation
  }

  "The date and time must parse correctly from an active GPRMC sentence" in {
    val gprmc = GPRMC.fromSentence("$GPRMC,034444.000,A,4110.1833,N,10449.5843,W,1.50,296.10,200315,,,A*7D")
    val expectedDateTime = LocalDateTime.parse("2015-03-20T03:44:44.000")

    gprmc.dateTime mustBe expectedDateTime
  }

  "The location must convert to Google Maps" in {
    val location = Location(Angle(North, 41, 10.1833), Angle(West, 104, 49.5843))

    location.toGmaps mustBe "41.16972166670693,-104.826404999627"
  }
}
