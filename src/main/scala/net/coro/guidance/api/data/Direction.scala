package net.coro.guidance.api.data

sealed trait Direction {
  def name: String

  def reverseAngle: Boolean = false
}

case object North extends Direction {
  val name = "North"
}

case object East extends Direction {
  val name = "East"
}

case object South extends Direction {
  val name = "South"
  override val reverseAngle = true
}

case object West extends Direction {
  val name = "West"
  override val reverseAngle = true
}
