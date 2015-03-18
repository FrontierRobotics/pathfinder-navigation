package net.coro.guidance.api.route

import net.coro.TestSpec
import spray.testkit.ScalatestRouteTest

class GuidanceApiTest extends TestSpec with ScalatestRouteTest with GuidanceApi {
  def actorRefFactory = system

  "The guidance api" must {
    "not handle request" when {
      "given wrong path" in {
        Get("/wrong") ~> guidanceRoute ~> check {
          handled must be(false)
        }
      }
    }
  }
}
