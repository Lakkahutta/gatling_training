package config

import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef.http
import io.gatling.core.Predef._
import io.gatling.core.check.MultipleFindCheckBuilder
import io.gatling.core.check.regex.{RegexCheckType, RegexOfType}
import io.gatling.http.protocol.HttpProtocolBuilder

object BaseHelpers {
  val baseUrl = "http://localhost/"
  val ajaxRequest = "http://localhost/wp-admin/admin-ajax.php"

  val addToCartChairBody: String = "action=ic_add_to_cart&add_cart_data=current_product%3D${chairId}" +
    "%26cart_content%3D${tableId}%26current_quantity%3D1&cart_widget=0&cart_container=0"
  val addToCartTableBody: String = "action=ic_add_to_cart&add_cart_data=current_product%3D${tableId}" +
    s"%26cart_content%3D%26current_quantity%3D1&cart_widget=0&cart_container=0"
  val tableCheckoutBody: String = "cart_content=%7B\"${tableId}__\":1%7D&p_id%5B%5D=${tableId}__&p_quantity%5B%5D=1&" +
    "total_net=${tablePrice}.00&trans_id=16871972518849&shipping=order"
  val tableAndChairCheckoutBody: String = "cart_content=%7B\"${tableId}__\":1,\"${chairId}__\":1%7D&p_id%5B%5D=${tableId}__&" +
    "p_quantity%5B%5D=1&p_id%5B%5D=${chairId}__&p_quantity%5B%5D=1&total_net=${totalPrice}.00&" +
    "trans_id=16871972518849&shipping=order"
  val tableAndChairCartContent: String = "\\{\"${tableId}__\":1,\"${chairId}__\":1\\},\\{\"${tableId}__\":1,\"${chairId}__\":1\\}"
  val tableCartContent: String = "\\{\"${tableId}__\":1\\},\\{\"${tableId}__\":1\\}"

  val homepageRegex: MultipleFindCheckBuilder[RegexCheckType, String, String] with RegexOfType = regex("Performance testing Essentials")
  val addToCartRegex: MultipleFindCheckBuilder[RegexCheckType, String, String] with RegexOfType = regex("Added!(.*?)See your cart")
  val placeOrderRegex: MultipleFindCheckBuilder[RegexCheckType, String, String] with RegexOfType = regex("Thank You")
  val productRegex: MultipleFindCheckBuilder[RegexCheckType, String, String] with RegexOfType = regex("<a href=\"http://localhost/products/(.+?)\">")
  val verifyProductRegex: MultipleFindCheckBuilder[RegexCheckType, String, String] with RegexOfType = regex("<link rel=\"canonical\" href=\"http://localhost/products/${prod}\"")
  val findPriceRegex: MultipleFindCheckBuilder[RegexCheckType, String, String] with RegexOfType = regex("<td class=\".* red-price small-price ic-design\">\\n.*\\$(.+?)\\.00")
  val findIdRegex: MultipleFindCheckBuilder[RegexCheckType, String, String] with RegexOfType = regex("name=\"current_product\" value=\"(.+?)\">")
  val verifyTableAndChairCheckout: MultipleFindCheckBuilder[RegexCheckType, String, String] with RegexOfType = regex("name=\"cart_content\" value='\\{\"${tableId}__\":1,\"${chairId}__\":1\\}'")
  val verifyTableCheckout: MultipleFindCheckBuilder[RegexCheckType, String, String] with RegexOfType = regex("name=\"cart_content\" value='\\{\"${tableId}__\":1\\}'")

  def thinkTimer(Min: Int = 2, Max: Int = 5): ChainBuilder = {
    pause(Min, Max)
  }

  val table_category = "tables"
  val chair_category = "chairs"
  val cart_category = "cart"

  val multipartHeader = "multipart/form-data; boundary=----WebKitFormBoundarySMCoJZloepB1BArY"

  val httpProtocol: HttpProtocolBuilder = http
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-GB,en-US;q=0.9,en;q=0.8")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
}
