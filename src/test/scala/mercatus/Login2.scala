package mercatus

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Login2 extends Simulation {

	val httpProtocol = http
		.baseUrl("https://us-release.gomercatus.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png""", """.*\/cometd\/conneet.*""", """.*\/cometd\/.*"""), WhiteList())
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")

	val headers_0 = Map(
		"Accept" -> "application/json",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Content-Type" -> "application/json; charset=UTF-8",
		"Origin" -> "https://us-release.gomercatus.com",
		"Pragma" -> "no-cache",
		"X-Requested-With" -> "XMLHttpRequest",
		"tzOffset" -> "330")

	val headers_1 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Pragma" -> "no-cache",
		"X-Requested-With" -> "XMLHttpRequest")

    val uri2 = "https://app.pendo.io/data/ptm.gif/d3c1865d-54d8-4182-62f9-999ed06d010d"

	val scn = scenario("Login2")
		.exec(http("Login2_0")
			.post("/rest/wfTask/P")
			.headers(headers_0)
			.body(RawFileBody("Login2_0000_request.txt"))
			.resources(http("Login2_1")
			.get("/rest/wfTask/P/23990/-1?_=1548417470151")
			.headers(headers_1)
			.check(bodyBytes.is(RawFileBody("Login2_0001_response.txt"))),
            http("Login2_2")
			.get("/rest/wfTask/P/calculateOverallPercentage/23990?_=1548417470152")
			.headers(headers_1)
			.check(bodyBytes.is(RawFileBody("Login2_0002_response.txt"))))
			.check(bodyBytes.is(RawFileBody("Login2_0000_response.txt"))))
		.pause(46)
		.exec(http("Login2_3")
			.get(uri2 + "?v=2.15.15_prod&ct=1548421550714&jzb=eJztWUFvozgU_iuIvcZNISSBHEfdQy-dPXRWWs2MkDGGeMfGyJg21ar_fZ6dkEA6UUkksrsVkQK2Y_w-v_fZ7zP5-o-rX0rqrtxMkrpyJ26i5HNFVayZgGZvHoSB7wXLwJvPJu4Tq5iWKmYpPEFmCz_yQy9AURalUUgWKKFRhAI_vUWht5rNAg8GxITIutC9noHuteLQb611Wa2m07pCinKKK3qTS0EVwbqubogUU1akdHOz1oL_Vir5NyW6mvqzKLqdalz9MDOB5rJyVzBDrHKqt6UcBv_05fHx8wP0sJDgTjj0cxNdOPBFKc1wzbUjCGpXzbBQ0ZjxyvwAz2mmOTipqDmHeWqtjLnXiSte7g06d-WDz8ia8XRX9zyAhRUt9O-cigOiu_s_GzgWfbymOIUgKJav9R5gCXaQbXI03ehtsQ-MYxTvgtibbKPpYwmGblu67W8JvJ0xzvsYgVEvNFIKZGcE1wQ9MfpsgjyYWUHiHTVjsBcTWQB5CuvILZx9C8p4zdIGjLkXEuW11lSZym4UhIlmT0wzaug9YCz64OqDYN5F4J8RKFxQjhKZvtj572uNK86J29l-aNxt1xdKuCQ_usD2mJwtstaOMRioVnA4Einy_EG5e5jwbnO1S2Y7XbtwIE1ca_EI4GJ7n2jiY9r33KxYkXOKhExpL_9HXUDBGYDM6jsGU10p-JALVcY2_2GqXW5uaErtDaUUcyWl2DL5xG43FKuNOejS5tCh6V2Ly3NTOVg06yR-Vri8JNkeaYcTe_inz3d_ndw6eimlo3m9vv3AKBtQce4DxOwRsrhrmpIXDdlw5S_n3utkL2YJZ3bPPiVm_RCW_yhmRzE7itlRzI5idhSzo5gdxewoZkcx---J2YmbcZzDSF9dTjPtfu-o20XYUrfvvKpdLJfg2I-hbtuxlynmMVEUaxqXAlSI9dqOA-ZHx-6sv4wODJdYGNAXeW6HE0e89yDRn80KxwJAsqT9pPQxQRo6fCscx3yb6-H-tuVb0ZQMgZy7rZrv9mqu7415XD7U2qesxSzoTcMwug0-zD8GIw3_xzScg2c_Bg3vH_748tg-Wz9gQe-Lsj4k4EwqYVOwkiczYuM5c-7ucPBytTPswd1OKleyNkl_eIkYgoriqBK7wqZC4VW0Iljsfxw921r7lU93w7KnIXs8_YV6fJJZVlGNvPnAr0wskuaQ3K2ddU6_nGZbozv96tQMpQrnOU7A3hWmnjLMZT6MUP_IOeuNpG6nCm--WLx-_wnIt3iw")
			.resources(http("Login2_4")
			.get(uri2 + "?v=2.15.15_prod&ct=1548421550738&jzb=eJy9lEtv2zoQhf-KwG5N23rYFb0M0kU2zaJpgYsgEChyLLMhRYEcpTYu_N8zslPUahpAXTQGDHPomfnO4ev-f4aHDtiGKWvUI5uxOvgfEUKFxtF0uirKIksLUS7FcsaeTDToQ2X0UJGvM5GVacHFVmhRqjWvQQheZHrJy3ST50VKDaVSvm9xUg2l98FS3g6xi5vFoo88gAUZYd54B0FJ7ONcebcwrYb9fIfOfuiC_w4K4yLLhVguUMbHSJ1ouotsQw5laADPo4aaX329u7v9TBknSfSrLOWxGtuEvrwLxslwSJzi47CisIryCagEDVpan7a3liwihoF0nDF3uBmEsc2K2u6M1S9hRnpkgBY_WXC_pFzffHulQ3nLneZ5Mgz2ka8ThD3yYJodTiHnY3I6nUxbP7huPW96RAhTaNT-kkaHZLrPFqVpIfCt7Y1-R7LzWlq-9Z5IA_Yy_rd7eyYNzik96Q3XQTaNrIn3Do61kdY3U0jU-u9IJ0ClAkiEqnPVcAnH9MS0b5GpXX26z5TLUzY6zL8pSWnt_yTl6vb6v1eufz4Mydm-7-BNCZdM2s8R8vjyodI9PSPsy_kJ2FrZUOk9s7BF9jD8Xx8QaCrNVx-PD889TbdT")
			.check(bodyBytes.is(RawFileBody("Login2_0004_response.txt"))),
            http("Login2_5")
			.get(uri2 + "?v=2.15.15_prod&ct=1548421550732&jzb=eJztmVFv2jwUhv8Kym5xISRAzF2r9oKb9tM3Om2qKuQ4TvDqxJHtdEUT_73HCbTQlg06UamtkZBiY5_3HNt5HyVc_fbMvGTeyKOC0xuv7cVK_tJMTQ3Podvvh1HY88MI-3jQ9m655kaqKU_sjGDQw73IDxFOcYIjOkAxwxiFvaSLIn8UBKEPAQmlsirMTnNgeKUEjJsZU-pRp1NppJhgRLOjTOZMUWIqfURl3uFFwu6OZiYXX0olfzJqdKcXYNztGKJvNESC7lJ7I6iQqIyZ5iqD4OPz_y4nMKDOyI4-JzkbF2VloJMKmOOlUuWIysIoKaDTcCNgNYpKCCjIGNXEbVbOsDvjLdpePh_bnGDRIMqMi2TZ7EIqRLHCnAmWP2ZxOv62yuFBdpvUevRgMzqI7Rq9LipTsip30Xl9FVQKlCcoatkLnS8v7jSKDqsLJ7eVU6uYVcYwdRA1e15QwgzhoLlSzmVCBDIkRrFM5lBuAb8XTKFUVDyxSd3KNNXMIL9_2DVoMqmzANXNViH3WJjXH7NG1K4BDG9VHCWKZBmJQe8NSk84ETLbRQlC76dUC0ypYsSwaZlP7VHYVG_xYrtbkLj2LBiL_A2_CJ5k4vdfTuXk4vTHs6pX5tdqypcl25rCumbvieTi4QOTwc9qhVSQDCZeeYKlxru2v8Vzw6DL70fBov0IjxkpMru9L9MD-xEeOno4ejh6OHo4enwKemzAYjBcg0UqaWUP_zZW4G74UVgxOfs-Of7_7Hi17sv5p0zT_YnhIPE-IbH3ajpIOEh8Qkj08foTxR9fR-FeN_wwr6McJBwkHCQcJBwkXvMeajjY_dFiiIPIUcNRw1HDUcNR47M-WvwVEgOo6GNA4uRyMrk4f7bqsSla8EWl4jlR9d212ZxCc6rJ7U5nHvZ7fTNgb_a02mDlsIOW_TcEKZ7NLL8OeJcvzXYvT_kXpDxz1DdSbm6rVEpQerTUpn3YvXU--o59dOWiX60FrDtpEHYX1_foJJGb")
			.check(bodyBytes.is(RawFileBody("Login2_0005_response.txt"))))
			.check(bodyBytes.is(RawFileBody("Login2_0003_response.txt"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}