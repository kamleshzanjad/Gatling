package mercatus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object OtherUsersPerformingOtherTask {


  val feederUrl = csv("TestDataFiles/ConfigData/mercatusLoginOtherUsers.csv").circular
  val scnOtherUsersActivity = scenario("OtherUsers performing Random other task").feed(feederUrl)
    .exec(
      LoginOperation.methodLogin("OtherUsersActivity"),
      SelectParentTab.NavigateToTabName("OtherUsersActivity", "Projects"),
      SelectParentTab.NavigateToTabName("OtherUsersActivity", "Tasks"),
      SelectParentTab.NavigateToTabName("OtherUsersActivity", "Assets"),
      SelectParentTab.NavigateToTabName("OtherUsersActivity", "Projects"),
      SelectParentTab.NavigateToTabName("OtherUsersActivity", "Tasks"),
      SelectParentTab.NavigateToTabName("OtherUsersActivity", "Assets")
    )


}
