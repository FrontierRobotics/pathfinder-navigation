package net.coro

import org.scalatest.prop.PropertyChecks
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, MustMatchers, WordSpecLike}

trait TestSpec extends WordSpecLike with BeforeAndAfterAll with BeforeAndAfter with MustMatchers with PropertyChecks {

}
