package api

import config.BaseHelpers.{baseUrl, findIdRegex, findPriceRegex, verifyProductRegex}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object tableDetails {
  def product(): ChainBuilder = {
    exec(
      http("Table page")
        .get(baseUrl + "products/${prod}")
        .check(verifyProductRegex)
        .check(findPriceRegex.saveAs("tablePrice"))
        .check(findIdRegex.saveAs("tableId"))
    )
  }
}
