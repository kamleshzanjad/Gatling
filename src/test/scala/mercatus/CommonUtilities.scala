package mercatus

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Login {
	def methodLogin(requestName:String, feederFile:String) ={
//		val feederCredentials= csv("TestDataFiles\\ConfigData\\" + feederFile).circular
//		feed(feederCredentials)
			exec(http(requestName)
				.post("/external/getUserAuthType")
				.headers(headers_0)
				.formParam("userName", "${UserId}")
				//.check(status.is(302))
			)
			.pause(10)
			.exec(http(requestName)
				.post("/j_spring_security_check")
				.headers(headers_0)
				.formParam("j_username", "${UserId}")
				.formParam("j_password", "${Password}")
				//.check(status.is(302))
				)
			.pause(10)

				.exec(http(requestName)
					.get("/rest/userInfo?_=1548411351095")
					.headers(headers_2)
					.resources(http(requestName)
						.get("/app/js/libs/i18bundle/Messages.properties?_=1548411351096")
						.headers(headers_3)	,
						http(requestName)
							.get("/app/js/libs/i18bundle/Messages_en.properties?_=1548411351097")
							.headers(headers_3),
						http(requestName)
							.get("/app/js/libs/i18bundle/Messages_en-US.properties?_=1548411351098")
							.headers(headers_3),
						http(requestName)
							.get("/rest/userInfo/userLocalizationConfig?_=1548411351099")
							.headers(headers_2),
						http(requestName)
							.get("/rest/assets/assetConfig?_=1548411351100")
							.headers(headers_2),
						http(requestName)
							.get("/rest/userInfo/photo/download/?ts=1548411356580&scaleHeight=140&scaleWidth=140"),

						http(requestName)
							.get("/rest/wfTaskApproval/myTask/count?ts=1548411355944&_=1548411351101")
							.headers(headers_10),

						http(requestName)
							.get("/rest/assetHomePage/3341?_=1548411351102")
							.headers(headers_2),
						http(requestName)
							.get("/rest/assetTile/globalCalendar/3341?_=1548411351103")
							.headers(headers_2),
						http(requestName)
							.get("/rest/wfTask/A/globalTasks/3341?_=1548411351104")
							.headers(headers_2)
						,
						http(requestName)
							.post("/cometd/")
							.headers(headers_9)
							.body(RawFileBody("Login_0017_request.txt"))
							.check(bodyBytes.is(RawFileBody("Login_0017_response.txt"))))
					//.check(bodyBytes.is(RawFileBody("Login_0002_response.txt"))))
				)
				.pause(1)
	}
}
