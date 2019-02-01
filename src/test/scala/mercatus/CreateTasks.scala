package mercatus

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

object CreateTasks {
  val navToTask = exec(http("navToTask")
    //	.get("/index.html#projects/${varProjectId}/tasks")
    .get("/index.html#projects/21011/tasks")
    .headers(Config.headers_10)
  ).pause(10)

  val createTask = exec(http("CreateTask")
    .post("/rest/wfTask/P")
    .headers(Config.headers_0)
    .body(RawFileBody("TestDataFiles/AddTask.json"))
  ).pause(10)
}