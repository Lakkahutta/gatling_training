package api

import config.BaseHelpers.{baseUrl, tableAndChairCheckoutBody, tableCheckoutBody, verifyTableAndChairCheckout, verifyTableCheckout}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object checkout {
  def checkout(): ChainBuilder = {
    doIfOrElse(session => session.contains("chairId")) {
      exec(session => {
        val tablePrice = session("tablePrice")
        val chairPrice = session("chairPrice")
        val totalPrice = tablePrice.as[String].toInt + chairPrice.as[String].toInt
        session.set("totalPrice", totalPrice.toString)
      })
      .exec(
        http("Checkout")
          .post(baseUrl + "checkout")
          .body(StringBody(tableAndChairCheckoutBody)).asFormUrlEncoded
          .check(verifyTableAndChairCheckout)
      )
      }
    {
      exec(
        http("Checkout")
          .post(baseUrl + "checkout")
          .body(StringBody(tableCheckoutBody)).asFormUrlEncoded
          .check(verifyTableCheckout)
      )
    }
  }
}
