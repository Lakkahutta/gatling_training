package simulations

import io.gatling.core.Predef._
import scenarios.buyProduct.scnBuyProduct
import config.BaseHelpers._

class PerfTestSimulation extends Simulation{
  //command to start test: mvn clean gatling:test -DTrainingTaskUsers=100

  setUp(
    scnBuyProduct.inject(atOnceUsers(System.getProperty("TrainingTaskUsers", "1").toInt))
  ).protocols(httpProtocol)
}
