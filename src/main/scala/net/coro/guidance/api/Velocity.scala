package net.coro.guidance.api

case class Velocity(kilometersPerHour: Double)

object Velocity {
  def fromKnots(knots: Double): Velocity = {
    Velocity(knots * 1.852) //exact
  }

  def fromMilesPerHour(mph: Double): Velocity = {
    Velocity(mph * 1.609344) //exact
  }

  def fromMetersPerSecond(mps: Double): Velocity = {
    Velocity(mps * 3.6) //exact
  }
}
