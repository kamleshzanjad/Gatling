package testcases

import io.gatling.core.Predef._
import scala.concurrent.duration._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import mercatus._

import OtherUsersPerformingOtherTask.scnOtherUsersActivity

class TC02 extends Simulation {

  val feederUrl = csv("TestDataFiles/ConfigData/mercatusLogin.csv").circular

  val DealRoomRenderingFor200Projects = scenario("DealRoomRenderingFor200Projects").feed(feederUrl)
    .exec(
      LoginOperation.methodLogin("Setup"),
      SelectParentTab.NavigateToTabName("Navigate to Tab: ", "Projects"))

  setUp(
    DealRoomRenderingFor200Projects.inject(rampUsers(2) during (60 seconds)),
    scnOtherUsersActivity.inject(rampUsers(2) during (60 seconds))
  ).maxDuration(10 minutes).protocols(Config.httpProtocol)
}

//mvn gatling:test -Dgatling.simulationClass=testcases.TC02   > logFiles/a.txt


