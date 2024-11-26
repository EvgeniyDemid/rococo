package api.helpers;

import io.qameta.allure.okhttp3.AllureOkHttp3;

public class CustomAllureListenerRetrofit {

	private static final AllureOkHttp3 FILTER = new AllureOkHttp3();

	public static AllureOkHttp3 withCustomTemplatesRetrofit(){
		FILTER.setRequestTemplate("request.ftl");
		FILTER.setResponseTemplate("response.ftl");
		return FILTER;
	}
}
