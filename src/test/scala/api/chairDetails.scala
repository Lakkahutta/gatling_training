package api

import config.BaseHelpers.{baseUrl, findIdRegex, findPriceRegex, verifyProductRegex}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object chairDetails {
  def product(): ChainBuilder = {
    exec(
      http("Chair page")
        .get(baseUrl + "products/${prod}")
        .check(verifyProductRegex)
        .check(findPriceRegex.saveAs("chairPrice"))
        .check(findIdRegex.saveAs("chairId"))
    )
  }
}
