package net.coro.guidance.api.route

import net.coro.TestSpec
import net.coro.guidance.api.data.{Angle, Location}
import net.coro.guidance.api.resource.LocationTracker
import spray.testkit.ScalatestRouteTest

class GuidanceApiTest extends TestSpec with ScalatestRouteTest with GuidanceApi {
  override val locationTracker = new LocationTracker()

  def actorRefFactory = system

  "The guidance api" must {
    "not handle request" when {
      "given wrong path" in {
        Get("/wrong") ~> guidanceRoute ~> check {
          handled must be(false)
        }
      }
    }

    "get a response for" when {
      "POST to gps/gprmc" in {
        Post("/gps/gprmc", "$GPRMC,034444.000,A,4110.1833,N,10449.5843,W,1.50,296.10,200315,,,A*7D") ~> guidanceRoute ~> check {
          responseAs[String] === "Data Received"
        }
      }
    }

    "have the current location" when {
      "given a NMEA sentence" in {
        val expectedLocation = new Location(new Angle(41,10.1833), new Angle(-104,49.5843))

        Post("/gps/gprmc", "$GPRMC,034444.000,A,4110.1833,N,10449.5843,W,1.50,296.10,200315,,,A*7D") ~> guidanceRoute ~> check {
          locationTracker.getCurrent.get mustBe expectedLocation
        }
      }
    }
  }

}
