package api

import config.BaseHelpers.{addToCartRegex, addToCartTableBody, ajaxRequest}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object addToCartTable {
  def addToCartTable(): ChainBuilder = {
    exec(
      http("Add to cart table")
        .post(ajaxRequest)
        .body(StringBody(addToCartTableBody)).asFormUrlEncoded
        .check(addToCartRegex)
    )
  }
}
