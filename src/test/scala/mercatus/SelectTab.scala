package mercatus

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

object SelectTab {
  def NavigateToTabName(tabName: String) = exec(http("SelectTab: " + tabName)
    .get("/index.html#" + tabName))
    .pause(10)

  val SelectHome = exec(this.NavigateToTabName("home"))
  val SelectConversations = exec(this.NavigateToTabName("conversations"))
  val SelectProjects = exec(this.NavigateToTabName("projects"))
  val SelectAssets = exec(this.NavigateToTabName("assets"))
  val SelectInvestors = exec(this.NavigateToTabName("investors"))
  val SelectContacts = exec(this.NavigateToTabName("contacts"))
  val SelectMyTasks = exec(this.NavigateToTabName("mytasks")) ///Total number s 		////Total number project(s)
  val SelectDocuments = exec(this.NavigateToTabName("documents"))
  val SelectAnalytics = exec(this.NavigateToTabName("analysis"))
  val SelectReports = exec(this.NavigateToTabName("reports"))
  val SelectPreferences = exec(this.NavigateToTabName("preferences"))
}