package mercatus

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

object GetProjectList {
  val getProjectList = exec(http("GetProjectList")
    .post("/rest/projects/637")
    .headers(Config.headers_10)
    ///.check(regex("""<input type="hidden" name="myvar" value="(.*)"/>""").saveAs("input")

    .check(regex(""""shortName":"ProjectCopied23930","category":"Project","organizationId":null,"orgId":637,"projectId":"(.*)","updatedBy"""").saveAs("varProjectId"))

  ).pause(10) ///response of this post

  ////{"data":{"projectList":[{"shortName":"ProjectCopied23930","category":"Project","organizationId":null,"orgId":637,"projectId":23990,"updatedBy":"kamlesh.zanjad@synerzip.com","evaluatedAt":1548
}
