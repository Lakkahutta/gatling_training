package api

import config.BaseHelpers.{baseUrl, productRegex, table_category}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object category {
  def category(page: String = table_category): ChainBuilder = {
    exec(
      http("Category page")
        .get(baseUrl + page)
        .check(regex(s"<h1 class=\"entry-title\">${page.capitalize}</h1>"))
        .check(productRegex.findRandom.saveAs("prod"))
    )
  }
}
