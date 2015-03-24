package net.coro.guidance.api.data

import net.coro.TestSpec
import net.coro.guidance.api.Velocity

class VelocityTest extends TestSpec {
  "The velocity must convert from knots to mph" in {
    val velocity = Velocity.fromKnots(1)

    //TODO
  }
}
