package api

import config.BaseHelpers.{addToCartChairBody, addToCartRegex, ajaxRequest}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object addToCartChair {
  def addToCartChair(): ChainBuilder = {
    exec(
      http("Add to cart chair")
        .post(ajaxRequest)
        .body(StringBody(addToCartChairBody)).asFormUrlEncoded
        .check(addToCartRegex)
    )
  }
}
