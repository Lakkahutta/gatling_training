package api

import config.BaseHelpers.{baseUrl, homepageRegex}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object home {
  def trainingHome(): ChainBuilder = {
    exec(
      http("Training Home")
        .get(baseUrl)
        .check(homepageRegex)
    )
  }
}
