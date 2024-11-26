package api.user;

import api.ApiClient;
import io.qameta.allure.Step;
import jupiter.extension.ApiLoginExtension;
import model.UserJson;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class UserServiceClient extends ApiClient {

	private final UserService userService;

	public UserServiceClient() {
		super(
				CFG.gatewayUrl(),
				JacksonConverterFactory.create(),
				HttpLoggingInterceptor.Level.BODY
		);
		this.userService = retrofit.create(UserService.class);
	}

	@Step("Получить профиль пользователя ")
	public UserJson userProfile() throws IOException {
		String token = "Bearer " + ApiLoginExtension.getToken();
		return userService.user(token).execute().body();
	}

	@Step("Обновить профиль пользователя ")
	public UserJson userProfileUpdate(UserJson userJson) throws IOException {
		String token = "Bearer " + ApiLoginExtension.getToken();
		return userService.userProfileUpdate(token, userJson).execute().body();
	}
}
