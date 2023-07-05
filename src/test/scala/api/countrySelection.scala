package api

import config.BaseHelpers.ajaxRequest
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object countrySelection {
  def countrySelection(countryCode: String = "CA"): ChainBuilder = {
    exec(
      http("Select country")
        .post(ajaxRequest)
        .body(StringBody(s"action=ic_state_dropdown&country_code=${countryCode}&state_code=")).asFormUrlEncoded
    )
  }
}
