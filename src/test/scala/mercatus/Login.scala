package mercatus

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

object Login {

  val feederCredentials = csv("TestDataFiles\\ConfigData\\mercatusLogin.csv").queue

  val login = feed(feederCredentials)
    .exec(http("LoginEnterUserId")
      .post("/external/getUserAuthType")
      .headers(Config.headers_0)
      .formParam("userName", "${UserId}")
      .check(status.is(302)))
    .pause(10)
    .exec(http("LoginEnterPassword")
      .post("/j_spring_security_check")
      .headers(Config.headers_0)
      .formParam("j_username", "${UserId}")
      .formParam("j_password", "${Password}")
      .check(status.is(302)))
    .pause(10)
}