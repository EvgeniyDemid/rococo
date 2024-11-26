package api.helpers;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomAllureListenerRestAssured {
	private static final AllureRestAssured FILTER = new AllureRestAssured();

	public static AllureRestAssured withCustomTemplatesRestAssured() {
		FILTER.setRequestTemplate("request.ftl");
		FILTER.setResponseTemplate("response.ftl");
		return FILTER;
	}
}
