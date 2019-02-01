package mercatus
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._


class Login extends Simulation {
  //https://us-development.gomercatus.com/
	//
	//
	//
	//Login
	//https://us-development.gomercatus.com     mercatus@synerzip.com / Test@1234
	val varProjectId:String =null
	//val feederUrl= csv("TestDataFiles/ConfigData/mercatusUrl.csv")
	val baseUrl ="https://us-release.gomercatus.com/"  ///https://us-development.gomercatus.com
	val httpProtocol = 	   http
	   //feed(feederUrl)
		.baseUrl("https://us-development.gomercatus.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png""", """.*\/connect\/commet.*"""), WhiteList())
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0")
  	.disableFollowRedirect

	val headers_0 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	val headers_2 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_3 = Map(
		"Accept" -> "text/plain, */*; q=0.01",
		"Content-Type" -> "text/plain;charset=UTF-8",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_9 = Map(
		"Content-Type" -> "application/json;charset=UTF-8",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_10 = Map(
		"Accept" -> "application/json",
		"Content-Type" -> "application/json; charset=utf-8",
		"X-Requested-With" -> "XMLHttpRequest")

//UserId,Password
object Login {
	val feederCredentials= csv("TestDataFiles\\ConfigData\\mercatusLogin.csv").queue
	val login=	feed(feederCredentials)
	  .exec(http("LoginEnterUserId")
		.post("/external/getUserAuthType")
		.headers(headers_0)
		.formParam("userName", "${UserId}")
			.check(status.is(302)))
		.pause(10)
		.exec(http("LoginEnterPassword")
		.post("/j_spring_security_check")
		.headers(headers_0)
		.formParam("j_username", "${UserId}")
		.formParam("j_password", "${Password}")
		.check(status.is(302)))
		.pause(10)
}
	object SelectTab{
		def NavigateToTabName(tabName: String) = exec(http("SelectTab: " +tabName)
		.get("/index.html#" + tabName))
  		.pause(10)
		val SelectHome= exec(this.NavigateToTabName("home"))
		val SelectConversations= exec(this.NavigateToTabName("conversations"))
		val SelectProjects = exec(this.NavigateToTabName("projects"))
		val SelectAssets = exec(this.NavigateToTabName("assets"))
		val SelectInvestors= exec(this.NavigateToTabName("investors"))
		val SelectContacts = exec(this.NavigateToTabName("contacts"))
		val SelectMyTasks= exec(this.NavigateToTabName("mytasks"))  ///Total number s 		////Total number project(s)
		val SelectDocuments = exec(this.NavigateToTabName("documents"))
		val SelectAnalytics= exec(this.NavigateToTabName("analysis"))
		val SelectReports = exec(this.NavigateToTabName("reports"))
		val SelectPreferences = exec(this.NavigateToTabName("preferences"))
	}

	object GetProjectList{
		val getProjectList =exec(http("GetProjectList")
			.post("/rest/projects/637")
			.headers(headers_10)
			///.check(regex("""<input type="hidden" name="myvar" value="(.*)"/>""").saveAs("input")

			.check(regex(""""shortName":"ProjectCopied23930","category":"Project","organizationId":null,"orgId":637,"projectId":"(.*)","updatedBy"""").saveAs("varProjectId"))

		).pause(10)		///response of this post

		////{"data":{"projectList":[{"shortName":"ProjectCopied23930","category":"Project","organizationId":null,"orgId":637,"projectId":23990,"updatedBy":"kamlesh.zanjad@synerzip.com","evaluatedAt":1548
	}


	object CreateTasks{
		val navToTask =exec(http("navToTask")
		//	.get("/index.html#projects/${varProjectId}/tasks")
			.get("/index.html#projects/21011/tasks")
			.headers(headers_10)

		).pause(10)

			val createTask = exec(http("CreateTask")
  			.post("/rest/wfTask/P")
				.headers(headers_0)
				.body(RawFileBody("TestDataFiles/AddTask.json"))

			).pause(10)
	}


//	{"taskName":"Task","entityId":23990,"entityType":"P","templateId":23990,"description":"Task","predLink":"","plannedDuration":"","projectedDuration":"","actualDuration":"","assignedTo":[],"approver":[],"milestone":false,"priority":"Not Prioritized","strActualStartDt":null,"strActualEndDt":null,"strPlannedStartDt":null,"strPlannedFinishDt":null,"strProjectedStartDt":null,"strProjectedFinishDt":null,"selectedTaskId":null,"docRequiredInd":false,"documentName":"","recurrenceType":"NONE","recurrenceCount":"1","categoryId":"","taskType":"Task"}



///GetProjectList.getProjectList,
//val scCreateTask = scenario("Create Task").exec(Login.login, SelectTab.SelectProjects,
//	 CreateTasks.createTask )
//
//
//	setUp(
//		scCreateTask.inject(rampUsers(10) over (20 seconds))).protocols(httpProtocol)


//	setUp(scCreateTask.inject(atOnceUsers(10)).protocols(httpProtocol))
//	setUp(
//		scCreateTask.inject(rampUsers(10) over (10 seconds))
//		).protocols(httpProtocol)

	//setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}



////https://github.com/gatling/gatling-maven-plugin-demo
///$      mvn gatling:test -Dgatling.simulationClass=computerdatabase.BasicSimulation
//or simply:
//
//$   mvn gatling:test
//mvn gatling:test -Dgatling.simulationClass=mercatus.Login   > logFiles/a.txt




//issues in find csv and json file
///
//Failed to build request: Resource AddTask.json      1 (50.00%)
//not found
//> regex("shortName":"ProjectCopied23930","category":"Project","o
//


/*
val scn = scenario("Login")
// Login
.exec(http("Login_0")
.post("/external/getUserAuthType")
.headers(headers_0)
.formParam("userName", "kamlesh.zanjad@synerzip.com"))
.pause(19)
// EnterPassword
.exec(http("Login_1")
.post("/j_spring_security_check")
.headers(headers_0)
.formParam("j_username", "kamlesh.zanjad@synerzip.com")
.formParam("j_password", "Test@12345"))
.pause(1)
.exec(http("Login_2")
.get("/rest/userInfo?_=1548411351095")
.headers(headers_2)
.resources(http("Login_3")
.get("/app/js/libs/i18bundle/Messages.properties?_=1548411351096")
.headers(headers_3)
.check(bodyBytes.is(RawFileBody("Login_0003_response.txt"))),
http("Login_4")
.get("/app/js/libs/i18bundle/Messages_en.properties?_=1548411351097")
.headers(headers_3)
.check(bodyBytes.is(RawFileBody("Login_0004_response.txt"))),
http("Login_5")
.get("/app/js/libs/i18bundle/Messages_en-US.properties?_=1548411351098")
.headers(headers_3)
.check(bodyBytes.is(RawFileBody("Login_0005_response.txt"))),
http("Login_6")
.get("/rest/userInfo/userLocalizationConfig?_=1548411351099")
.headers(headers_2)
.check(bodyBytes.is(RawFileBody("Login_0006_response.txt"))),
http("Login_7")
.get("/rest/assets/assetConfig?_=1548411351100")
.headers(headers_2)
.check(bodyBytes.is(RawFileBody("Login_0007_response.txt"))),
http("Login_8")
.get("/rest/userInfo/photo/download/?ts=1548411356580&scaleHeight=140&scaleWidth=140"),
http("Login_9")
.post("/cometd/handshake")
.headers(headers_9)
.body(RawFileBody("Login_0009_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0009_response.txt"))),
http("Login_10")
.get("/rest/wfTaskApproval/myTask/count?ts=1548411355944&_=1548411351101")
.headers(headers_10)
.check(bodyBytes.is(RawFileBody("Login_0010_response.txt"))),
http("Login_11")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0011_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0011_response.txt"))),
http("Login_12")
.get("/rest/assetHomePage/3341?_=1548411351102")
.headers(headers_2)
.check(bodyBytes.is(RawFileBody("Login_0012_response.txt"))),
http("Login_13")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0013_request.txt"))
.check(status.is(500))
.check(bodyBytes.is(RawFileBody("Login_0013_response.txt"))),
http("Login_14")
.get("/rest/assetTile/globalCalendar/3341?_=1548411351103")
.headers(headers_2)
.check(bodyBytes.is(RawFileBody("Login_0014_response.txt"))),
http("Login_15")
.get("/rest/wfTask/A/globalTasks/3341?_=1548411351104")
.headers(headers_2)
.check(bodyBytes.is(RawFileBody("Login_0015_response.txt"))),
http("Login_16")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0016_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0016_response.txt"))),
http("Login_17")
.post("/cometd/")
.headers(headers_9)
.body(RawFileBody("Login_0017_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0017_response.txt"))))
.check(bodyBytes.is(RawFileBody("Login_0002_response.txt"))))
.pause(1)
.exec(http("Login_18")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0018_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0018_response.txt"))))
.pause(2)
.exec(http("Login_19")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0019_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0019_response.txt"))))
.pause(2)
.exec(http("Login_20")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0020_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0020_response.txt"))))
.pause(2)
.exec(http("Login_21")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0021_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0021_response.txt"))))
.pause(2)
.exec(http("Login_22")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0022_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0022_response.txt"))))
.pause(2)
// NavigateToProjects
.exec(http("Login_23")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0023_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0023_response.txt"))))
.pause(2)
.exec(http("Login_24")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0024_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0024_response.txt"))))
.pause(2)
.exec(http("Login_25")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0025_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0025_response.txt"))))
.pause(2)
.exec(http("Login_26")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0026_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0026_response.txt"))))
.pause(2)
.exec(http("Login_27")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0027_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0027_response.txt"))))
.pause(2)
.exec(http("Login_28")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0028_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0028_response.txt"))))
.pause(2)
.exec(http("Login_29")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0029_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0029_response.txt"))))
.pause(2)
.exec(http("Login_30")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0030_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0030_response.txt"))))
.pause(2)
.exec(http("Login_31")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0031_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0031_response.txt"))))
.pause(2)
.exec(http("Login_32")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0032_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0032_response.txt"))))
.pause(2)
.exec(http("Login_33")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0033_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0033_response.txt"))))
.pause(2)
.exec(http("Login_34")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0034_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0034_response.txt"))))
.pause(2)
.exec(http("Login_35")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0035_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0035_response.txt"))))
.pause(2)
.exec(http("Login_36")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0036_request.txt"))
.resources(http("Login_37")
.get("/rest/wfTask/P/settings/org/637/includeWeekend?_=1548411351105")
.headers(headers_2)
.check(bodyBytes.is(RawFileBody("Login_0037_response.txt"))),
http("Login_38")
.get("/rest/assumptionPermission/userPermission?_=1548411351106")
.headers(headers_10)
.check(bodyBytes.is(RawFileBody("Login_0038_response.txt"))),
http("Login_39")
.post("/rest/projects/637")
.headers(headers_10)
.body(RawFileBody("Login_0039_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0039_response.txt"))),
http("Login_40")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0040_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0040_response.txt"))))
.check(bodyBytes.is(RawFileBody("Login_0036_response.txt"))))
.pause(2)
.exec(http("Login_41")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0041_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0041_response.txt"))))
.pause(2)
.exec(http("Login_42")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0042_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0042_response.txt"))))
.pause(2)
.exec(http("Login_43")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0043_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0043_response.txt"))))
.pause(2)
// SelectProject
.exec(http("Login_44")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0044_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0044_response.txt"))))
.pause(2)
.exec(http("Login_45")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0045_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0045_response.txt"))))
.pause(2)
.exec(http("Login_46")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0046_request.txt"))
.resources(http("Login_47")
.get("/rest/users/adminOrgUsers/637?_=1548411351107")
.headers(headers_2)
.check(bodyBytes.is(RawFileBody("Login_0047_response.txt"))),
http("Login_48")
.get("/rest/summary/23930?_=1548411351108")
.headers(headers_2)
.check(bodyBytes.is(RawFileBody("Login_0048_response.txt"))),
http("Login_49")
.get("/rest/summary/summaryGraph/23930?_=1548411351109")
.headers(headers_2)
.check(bodyBytes.is(RawFileBody("Login_0049_response.txt"))),
http("Login_50")
.post("/cometd/connect")
.headers(headers_9)
.body(RawFileBody("Login_0050_request.txt"))
.check(bodyBytes.is(RawFileBody("Login_0050_response.txt"))))
.check(bodyBytes.is(RawFileBody("Login_0046_response.txt"))))

*/