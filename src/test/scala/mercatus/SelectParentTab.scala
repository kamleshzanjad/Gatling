package mercatus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SelectParentTab {

  def NavigateToTabName(requestName: String, tabName: String) = {
    exec(http(requestName)
      .get("/index.html#" + tabName)
      .check(status.is(302))
    )
      .pause(10)
  }


}
