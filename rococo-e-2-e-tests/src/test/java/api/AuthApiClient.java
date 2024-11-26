package api;

import api.cookie.ThreadSafeCookieStore;
import api.interceptor.CodeInterceptor;
import jupiter.extension.ApiLoginExtension;
import lombok.SneakyThrows;
import model.oauth.TokenJson;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.jackson.JacksonConverterFactory;
import utils.OauthUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthApiClient extends ApiClient {

	private final AuthApi authApi;

	public AuthApiClient() {
		super(
				CFG.authUrl(),
				true,
				JacksonConverterFactory.create(),
				HttpLoggingInterceptor.Level.BODY,
				new CodeInterceptor()
		);
		this.authApi = retrofit.create(AuthApi.class);
	}

	@SneakyThrows
	public void doLogin(String username, String password) {
		final String codeVerifier = OauthUtils.generateCodeVerifier();
		final String codeChallenge = OauthUtils.generateCodeChallange(codeVerifier);

		authApi.preRequest(
				"code",
				"client",
				"openid",
				CFG.frontUrl() + "/authorized",
				codeChallenge,
				"S256"
		).execute();

		authApi.login(
				ThreadSafeCookieStore.INSTANCE.getCookieValue("XSRF-TOKEN"),
				username,
				password
		).execute();

		TokenJson response = authApi.token(
				"Basic " + Base64.getEncoder().encodeToString("client:secret".getBytes(StandardCharsets.UTF_8)),
				"client",
				CFG.frontUrl() + "/authorized",
				"authorization_code",
				ApiLoginExtension.getCode(),
				codeVerifier
		).execute().body();

		assert response != null;
		ApiLoginExtension.setToken(response.idToken());
	}
}
