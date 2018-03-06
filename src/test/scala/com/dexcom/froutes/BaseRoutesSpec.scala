package com.dexcom.froutes

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FunSpec, Matchers}
import org.scalacheck.Gen
import org.scalamock.scalatest.MockFactory
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class BaseRoutesSpec
    extends FunSpec
    with Matchers
    with ScalatestRouteTest
    with GeneratorDrivenPropertyChecks
    with MockFactory {

  private val mockBaseRepo = mock[BaseRepository]

  private val testRoutes = BaseRoutes.routes(mockBaseRepo)

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

  describe("uptime") {
    it("returns a duration") {
      (mockBaseRepo.durationFromBoot _)
        .expects(*)
        .returns("PT1M38.12S")

      val testRequest = Get("/uptime")

      testRequest ~> testRoutes ~> check {
        responseAs[String] should ===("PT1M38.12S")
      }
    }
  }
}
