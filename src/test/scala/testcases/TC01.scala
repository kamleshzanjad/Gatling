package testcases

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import mercatus._

class TC01 extends Simulation {

  val scCreateTask = scenario("Create Task").exec(Login.login, SelectTab.NavigateToTabName("Projects"), SelectTab.SelectProjects,
    CreateTasks.createTask)

  setUp(scCreateTask.inject(rampUsers(1) during (6 seconds)).protocols(Config.httpProtocol))
}