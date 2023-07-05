package scenarios

import config.BaseHelpers._
import io.gatling.core.structure._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object buyProduct {
  def scnBuyProduct: ScenarioBuilder = {
    val checkoutCsvFeeder = csv("src/test/resources/feeders/checkout_data.csv").random

    scenario("Buy product")
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .feed(checkoutCsvFeeder)
      .exitBlockOnFail(
        group("Home") {
          exec(api.home.trainingHome())
            .exec(thinkTimer())
        }
          .group("Open category") {
            exec(api.category.category())
              .exec(thinkTimer())
          }
          .group("Open random product") {
            exec(api.tableDetails.product())
              .exec(thinkTimer())
              .exec(api.addToCartTable.addToCartTable())
              .exec(thinkTimer())
          }
          .randomSwitch(
        50.0 ->
            group("Open category") {
              exec(api.category.category(chair_category))
                .exec(thinkTimer())
            }
              .group("Open random product") {
                exec(api.chairDetails.product())
                  .exec(thinkTimer())
                  .exec(api.addToCartChair.addToCartChair())
                  .exec(thinkTimer())
              .randomSwitch(
                30.0 ->
                  group("Open cart") {
                    exec(api.category.category(cart_category))
                      .exec(thinkTimer())
                      .exec(api.checkout.checkout())
                      .exec(thinkTimer())
                  }
                    .group("Place order") {
                      exec(api.countrySelection.countrySelection())
                        .exec(thinkTimer())
                        .exec(api.placeOrder.placeOrder("${name}", "${address}", "${postalCode}",
                          "${city}", "${country}", "${state}", "${phone}", "${email}"))
                        .exec(thinkTimer())
                    }
              )
            },
            30.0 ->
              group("Open cart") {
                exec(api.category.category(cart_category))
                  .exec(thinkTimer())
                  .exec(api.checkout.checkout())
                  .exec(thinkTimer())
              }
                .group("Place order") {
                  exec(api.countrySelection.countrySelection())
                    .exec(thinkTimer())
                    .exec(api.placeOrder.placeOrder("${name}", "${address}", "${postalCode}",
                      "${city}", "${country}", "${state}", "${phone}", "${email}"))
                    .exec(thinkTimer())
                }
          )
      )
  }
}
