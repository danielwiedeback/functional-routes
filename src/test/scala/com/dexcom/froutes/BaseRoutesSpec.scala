package com.dexcom.froutes

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FunSpec, Matchers}
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class BaseRoutesSpec
    extends FunSpec
    with Matchers
    with ScalatestRouteTest
    with GeneratorDrivenPropertyChecks {

  private val testRoutes = BaseRoutes.routes

  describe("integerRoute") {
    it("returns the expected string") {
      forAll(Gen.posNum[Int]) { number =>
        val testRequest = Get(s"/integer/$number")

        testRequest ~> testRoutes ~> check {
          responseAs[String] should ===(s"The number you provided was $number")
        }
      }
    }
  }
}
