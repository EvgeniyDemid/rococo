package tests.apiRetrofit;

import api.user.UserServiceClient;
import jupiter.annotation.ApiLogin;
import jupiter.annotation.TestUser;
import jupiter.annotation.meta.ApiTest;
import model.UserJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static enums.UserType.RANDOM_REGISTERED_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ApiTest
public class UserTest {
	UserServiceClient userServiceClient = new UserServiceClient();

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Проверить профиль пользователя")
	public void userProfile(UserJson userJson) throws IOException {
		UserJson actualUser = userServiceClient.userProfile();
		checkUserProfile(userJson,actualUser);
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Обновить профиль пользователя")
	public void userProfileUpdate(UserJson userJson) throws IOException {
		UserJson editUser = new UserJson(
				userJson.id(),
				userJson.username(),
				userJson.firstname() + "new",
				userJson.lastname() + "new",
				userJson.avatar(),
				userJson.password()
		);
		UserJson actualUser = userServiceClient.userProfileUpdate(editUser);
		checkUserProfile(editUser,actualUser);
	}

	private void checkUserProfile(UserJson expected, UserJson actual){
		assertEquals(expected.id(), actual.id(),"Id пользователя не совпадает");
		assertEquals(expected.firstname() , actual.firstname(),"Имя пользователя не совпадает");
		assertEquals(expected.lastname(), actual.lastname(),"Отчество пользователя не совпадает");
		assertEquals(expected.username(), actual.username(),"Фамилия пользователя не совпадает");
	}

}
