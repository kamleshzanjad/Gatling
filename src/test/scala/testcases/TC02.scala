package testcases

import io.gatling.core.Predef._
import scala.concurrent.duration._


import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import mercatus.{Login, _}

import OtherUsersPerformingOtherTask.scnOtherUsersActivity

class TC02 extends Simulation {
  val feederUrl = csv("TestDataFiles/ConfigData/mercatusLogin.csv").circular
  val DealRoomRenderingFor200Projects = scenario("DealRoomRenderingFor200Projects").feed(feederUrl)
    .exec(
      LoginOperation.methodLogin("Setup"),
      SelectParentTab.NavigateToTabName("Navigate to Tab: ", "Projects"))


  setUp(DealRoomRenderingFor200Projects.inject(atOnceUsers(1))).protocols(Config.httpProtocol)
  //  setUp(
  //    scCreateTask.inject(rampUsers(10) ),
  //    scnOtherUsersActivity.inject(rampUsers(5))
  //  ).protocols(Config.httpProtocol)

  //  setUp(
  //    DealRoomRenderingFor200Projects.inject(
  //      rampUsers(10) over (60 seconds)
  //    )
  //  )
  //    .maxDuration(30 minutes).protocols(Config.httpProtocol)
}


//mvn gatling:test -Dgatling.simulationClass=testcases.TC02   > logFiles/a.txt


