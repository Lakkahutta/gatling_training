package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object placeOrder {
  def placeOrder(name: String = "", address: String = "", postalCode: String = "", city: String = "",
                 country: String = "", state: String = "", phone: String = "", email: String = ""): ChainBuilder = {
    doIfOrElse(session => session.contains("chairId")) {
      exec(
        http("Place order with 2 items")
          .post(baseUrl + "checkout")
          .header("Content-Type", multipartHeader)
          .bodyPart(StringBodyPart("ic_formbuilder_redirect", "http://localhost/thank-you"))
          .formParam("cart_content", tableAndChairCartContent)
          .formParam("total_net", "${totalPrice}.00")
          .formParam("trans_id", "16871972586499")
          .formParam("shipping", "order")
          .formParam("cart_type", "order")
          .formParam("cart_name", name)
          .formParam("cart_address", address)
          .formParam("cart_postal", postalCode)
          .formParam("cart_city", city)
          .formParam("cart_country", country)
          .formParam("cart_state", state)
          .formParam("cart_phone", phone)
          .formParam("cart_email", email)
          .formParam("cart_submit", "Place Order")
          .formParam("product_price_${tableId}__", "${tablePrice}.00")
          .formParam("product_price_${chairId}__", "${chairPrice}.00")
          .check()
      )
    }
      {
        exec(
          http("Place order with 1 item")
            .post(baseUrl + "checkout")
            .header("Content-Type", multipartHeader)
            .bodyPart(StringBodyPart("ic_formbuilder_redirect", "http://localhost/thank-you"))
            .formParam("cart_content", tableCartContent)
            .formParam("total_net", "${tablePrice}.00")
            .formParam("trans_id", "16871972586499")
            .formParam("shipping", "order")
            .formParam("cart_type", "order")
            .formParam("cart_name", name)
            .formParam("cart_address", address)
            .formParam("cart_postal", postalCode)
            .formParam("cart_city", city)
            .formParam("cart_country", country)
            .formParam("cart_state", state)
            .formParam("cart_phone", phone)
            .formParam("cart_email", email)
            .formParam("cart_submit", "Place Order")
            .formParam("product_price_${tableId}__", "${tablePrice}.00")
            .check(regex("Thank You"))
        )
    }
  }
}

