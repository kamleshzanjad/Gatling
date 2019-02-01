package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class SampleRecord extends Simulation {

	val httpProtocol = http
		.baseUrl("https://us-release.gomercatus.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0")

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

	val headers_72 = Map(
		"Accept" -> "application/json",
		"Content-Type" -> "application/json; charset=utf-8",
		"X-Requested-With" -> "XMLHttpRequest",
		"tzOffset" -> "330")



	val scn = scenario("SampleRecord")
		// Login
		.exec(http("SampleRecord_0")
			.post("/external/getUserAuthType")
			.headers(headers_0)
			.formParam("userName", "kamlesh.zanjad@synerzip.com"))
		.pause(24)
		// enterPassword
		.exec(http("SampleRecord_1")
			.post("/j_spring_security_check")
			.headers(headers_0)
			.formParam("j_username", "kamlesh.zanjad@synerzip.com")
			.formParam("j_password", "Test@12345"))
		.pause(1)
		.exec(http("SampleRecord_2")
			.get("/rest/userInfo?_=1548406713509")
			.headers(headers_2)
			.resources(http("SampleRecord_3")
			.get("/app/js/libs/i18bundle/Messages.properties?_=1548406713510")
			.headers(headers_3)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0003_response.txt"))),
            http("SampleRecord_4")
			.get("/app/js/libs/i18bundle/Messages_en.properties?_=1548406713511")
			.headers(headers_3)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0004_response.txt"))),
            http("SampleRecord_5")
			.get("/app/js/libs/i18bundle/Messages_en-US.properties?_=1548406713512")
			.headers(headers_3)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0005_response.txt"))),
            http("SampleRecord_6")
			.get("/rest/userInfo/userLocalizationConfig?_=1548406713513")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0006_response.txt"))),
            http("SampleRecord_7")
			.get("/rest/assets/assetConfig?_=1548406713514")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0007_response.txt"))),
            http("SampleRecord_8")
			.get("/rest/userInfo/photo/download/?ts=1548406718482&scaleHeight=140&scaleWidth=140"),
            http("SampleRecord_9")
			.post("/cometd/handshake")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0009_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0009_response.txt"))),
            http("SampleRecord_10")
			.get("/rest/wfTaskApproval/myTask/count?ts=1548406718048&_=1548406713515")
			.headers(headers_10)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0010_response.txt"))),
            http("SampleRecord_11")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0011_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0011_response.txt"))),
            http("SampleRecord_12")
			.get("/rest/assetHomePage/3341?_=1548406713516")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0012_response.txt"))),
            http("SampleRecord_13")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0013_request.txt"))
			.check(status.is(500))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0013_response.txt"))),
            http("SampleRecord_14")
			.get("/rest/assetTile/globalCalendar/3341?_=1548406713517")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0014_response.txt"))),
            http("SampleRecord_15")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0015_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0015_response.txt"))),
            http("SampleRecord_16")
			.get("/rest/wfTask/A/globalTasks/3341?_=1548406713518")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0016_response.txt"))),
            http("SampleRecord_17")
			.post("/cometd/")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0017_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0017_response.txt"))),
            http("SampleRecord_18")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0018_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0018_response.txt"))))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0002_response.txt"))))
		.pause(2)
		// NavProjectsTab
		.exec(http("SampleRecord_19")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0019_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0019_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_20")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0020_request.txt"))
			.resources(http("SampleRecord_21")
			.get("/rest/wfTask/P/settings/org/637/includeWeekend?_=1548406713519")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0021_response.txt"))),
            http("SampleRecord_22")
			.get("/rest/assumptionPermission/userPermission?_=1548406713520")
			.headers(headers_10)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0022_response.txt"))),
            http("SampleRecord_23")
			.post("/rest/projects/637")
			.headers(headers_10)
			.body(RawFileBody("SampleRecord_0023_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0023_response.txt"))),
            http("SampleRecord_24")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0024_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0024_response.txt"))))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0020_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_25")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0025_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0025_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_26")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0026_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0026_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_27")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0027_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0027_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_28")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0028_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0028_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_29")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0029_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0029_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_30")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0030_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0030_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_31")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0031_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0031_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_32")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0032_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0032_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_33")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0033_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0033_response.txt"))))
		.pause(2)
		// SelectProject New Project
		.exec(http("SampleRecord_34")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0034_request.txt"))
			.resources(http("SampleRecord_35")
			.get("/rest/users/adminOrgUsers/637?_=1548406713521")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0035_response.txt"))),
            http("SampleRecord_36")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0036_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0036_response.txt"))),
            http("SampleRecord_37")
			.get("/app/js/views/summary/summaryTabView.js?v=20190123-2223")
			.check(bodyBytes.is(RawFileBody("SampleRecord_0037_response.txt"))),
            http("SampleRecord_38")
			.get("/app/js/views/summary/scoreCanvasTileView.js?v=20190123-2223")
			.check(bodyBytes.is(RawFileBody("SampleRecord_0038_response.txt"))),
            http("SampleRecord_39")
			.get("/app/js/views/summary/configurePopupView.js?v=20190123-2223")
			.check(bodyBytes.is(RawFileBody("SampleRecord_0039_response.txt"))),
            http("SampleRecord_40")
			.get("/app/js/templates/summary/summaryTabTpl.html?v=20190123-2223")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0040_response.txt"))),
            http("SampleRecord_41")
			.get("/app/js/templates/summary/mediumTileTableRowTpl.html?v=20190123-2223")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0041_response.txt"))),
            http("SampleRecord_42")
			.get("/app/js/templates/summary/starRatingRowTpl.html?v=20190123-2223")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0042_response.txt"))),
            http("SampleRecord_43")
			.get("/app/js/templates/summary/otherCostsPopupTpl.html?v=20190123-2223")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0043_response.txt"))),
            http("SampleRecord_44")
			.get("/app/js/libs/flotChart/jquery.flot.time.min.js?v=20190123-2223")
			.check(bodyBytes.is(RawFileBody("SampleRecord_0044_response.txt"))),
            http("SampleRecord_45")
			.get("/app/js/libs/flotChart/jquery.flot.canvas.min.js?v=20190123-2223")
			.check(bodyBytes.is(RawFileBody("SampleRecord_0045_response.txt"))),
            http("SampleRecord_46")
			.get("/app/js/libs/flotChart/jquery.flot.rangeselection.js?v=20190123-2223")
			.check(bodyBytes.is(RawFileBody("SampleRecord_0046_response.txt"))),
            http("SampleRecord_47")
			.get("/app/js/libs/flotChart/jquery.flot.stackbars.js?v=20190123-2223")
			.check(bodyBytes.is(RawFileBody("SampleRecord_0047_response.txt"))),
            http("SampleRecord_48")
			.get("/app/js/libs/flotChart/jquery.flot.labels.js?v=20190123-2223")
			.check(bodyBytes.is(RawFileBody("SampleRecord_0048_response.txt"))),
            http("SampleRecord_49")
			.get("/app/js/templates/summary/scoreCanvasTileTpl.html?v=20190123-2223")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0049_response.txt"))),
            http("SampleRecord_50")
			.get("/app/js/templates/summary/devCostsPopupTpl.html?v=20190123-2223")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0050_response.txt"))),
            http("SampleRecord_51")
			.get("/app/js/templates/summary/systemCostsPopupTpl.html?v=20190123-2223")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0051_response.txt"))),
            http("SampleRecord_52")
			.get("/app/js/templates/summary/configurePopupTpl.html?v=20190123-2223")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0052_response.txt"))),
            http("SampleRecord_53")
			.get("/rest/summary/23930?_=1548406713522")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0053_response.txt"))),
            http("SampleRecord_54")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0054_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0054_response.txt"))),
            http("SampleRecord_55")
			.get("/rest/summary/summaryGraph/23930?_=1548406713523")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0055_response.txt"))))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0034_response.txt"))))
		.pause(1)
		.exec(http("SampleRecord_56")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0056_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0056_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_57")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0057_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0057_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_58")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0058_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0058_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_59")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0059_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0059_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_60")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0060_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0060_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_61")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0061_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0061_response.txt"))))
		.pause(2)
		// NavToTask
		.exec(http("SampleRecord_62")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0062_request.txt"))
			.resources(http("SampleRecord_63")
			.get("/rest/wfTask/P/taskResources/637?_=1548406713524")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0063_response.txt"))),
            http("SampleRecord_64")
			.get("/rest/wfTask/P/fetchTaskFieldForUserRole/637?_=1548406713525")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0064_response.txt"))),
            http("SampleRecord_65")
			.post("/cometd/")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0065_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0065_response.txt"))),
            http("SampleRecord_66")
			.get("/rest/wfTask/P/filterConfig?_=1548406713526")
			.headers(headers_10)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0066_response.txt"))),
            http("SampleRecord_67")
			.get("/rest/wfTask/P/views?_=1548406713527")
			.headers(headers_10)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0067_response.txt"))),
            http("SampleRecord_68")
			.get("/rest/phaseDefinition/calculatePhase/23930?_=1548406713528")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0068_response.txt"))),
            http("SampleRecord_69")
			.get("/rest/wfTask/P/filters?_=1548406713529")
			.headers(headers_10)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0069_response.txt"))),
            http("SampleRecord_70")
			.get("/rest/wfTask/P/taskCategories?_=1548406713530")
			.headers(headers_10)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0070_response.txt"))),
            http("SampleRecord_71")
			.get("/rest/wfTask/P/calculateOverallPercentage/23930?_=1548406713531")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0071_response.txt"))),
            http("SampleRecord_72")
			.post("/rest/wfTask/P/updateProjectedDates")
			.headers(headers_72)
			.body(RawFileBody("SampleRecord_0072_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0072_response.txt"))),
            http("SampleRecord_73")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0073_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0073_response.txt"))),
            http("SampleRecord_74")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0074_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0074_response.txt"))),
            http("SampleRecord_75")
			.get("/rest/wfTask/P/23930/-1?_=1548406713532")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0075_response.txt"))),
            http("SampleRecord_76")
			.get("/rest/wfTask/P/calculateOverallPercentage/23930?_=1548406713533")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0076_response.txt"))),
            http("SampleRecord_77")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0077_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0077_response.txt"))))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0062_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_78")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0078_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0078_response.txt"))))
		.pause(2)
		// CreateTask
		.exec(http("SampleRecord_79")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0079_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0079_response.txt"))))
		.pause(1)
		.exec(http("SampleRecord_80")
			.get("/rest/wfTask/P/taskResources/637?_=1548406713534")
			.headers(headers_2)
			.resources(http("SampleRecord_81")
			.get("/rest/wfTask/P/fetchTaskFieldForUserRole/637?_=1548406713535")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0081_response.txt"))),
            http("SampleRecord_82")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0082_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0082_response.txt"))))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0080_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_83")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0083_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0083_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_84")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0084_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0084_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_85")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0085_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0085_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_86")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0086_request.txt"))
			.resources(http("SampleRecord_87")
			.post("/rest/wfTask/P")
			.headers(headers_72)
			.body(RawFileBody("SampleRecord_0087_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0087_response.txt"))),
            http("SampleRecord_88")
			.get("/rest/wfTask/P/23930/-1?_=1548406713536")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0088_response.txt"))),
            http("SampleRecord_89")
			.get("/rest/wfTask/P/calculateOverallPercentage/23930?_=1548406713537")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("SampleRecord_0089_response.txt"))),
            http("SampleRecord_90")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0090_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0090_response.txt"))),
            http("SampleRecord_91")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0091_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0091_response.txt"))))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0086_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_92")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0092_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0092_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_93")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0093_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0093_response.txt"))))
		.pause(2)
		// Logout
		.exec(http("SampleRecord_94")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0094_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0094_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_95")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0095_request.txt"))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0095_response.txt"))))
		.pause(2)
		.exec(http("SampleRecord_96")
			.post("/cometd/connect")
			.headers(headers_9)
			.body(RawFileBody("SampleRecord_0096_request.txt"))
			.resources(http("SampleRecord_97")
			.get("/j_spring_security_logout")
			.headers(headers_0)
			.check(status.is(302)))
			.check(bodyBytes.is(RawFileBody("SampleRecord_0096_response.txt"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}