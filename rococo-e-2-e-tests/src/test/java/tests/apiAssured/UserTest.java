package tests.apiAssured;

import jupiter.annotation.ApiLogin;
import jupiter.annotation.TestUser;
import jupiter.extension.ApiLoginExtension;
import model.UserJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.specs.UserSpec.userProfileRequestSpec;
import static api.specs.UserSpec.userProfileResponseSpec;
import static enums.UserType.RANDOM_REGISTERED_USER;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest extends BaseTest {

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Получить данные пользователя")
	public void userProfile(UserJson userJson) {
		UserJson user = step("Сделать запрос на получение профиля пользователя", () -> {
			return given(userProfileRequestSpec)
					.auth()
					.oauth2(ApiLoginExtension.getToken())
					.when()
					.get("/user")
					.then()
					.spec(userProfileResponseSpec)
					.extract()
					.as(UserJson.class);
		});
		step("Проверить полученные данные пользователя", () -> {
			checkUserProfile(userJson,user);
		});

	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Изменить данные пользователя")
	public void editUserProfile(UserJson userJson){
		UserJson newUser = new UserJson(
				userJson.id(),
				userJson.username(),
				userJson.firstname() + "new",
				userJson.lastname() + "new",
				userJson.avatar(),
				userJson.password()
		);
		UserJson user = step("Сделать запрос на изменение профиля пользователя", () -> {
			return given(userProfileRequestSpec)
					.auth()
					.oauth2(ApiLoginExtension.getToken())
					.body(newUser)
					.when()
					.patch("/user")
					.then().
					spec(userProfileResponseSpec)
					.extract()
					.as(UserJson.class);
		});
		step("Проверить полученные данные пользователя", () -> {
			checkUserProfile(newUser,user);
		});
		UserJson userAfterUpdate = step("Сделать запрос на получение профиля пользователя", () -> {
			return given(userProfileRequestSpec)
					.auth()
					.oauth2(ApiLoginExtension.getToken())
					.when()
					.get("/user")
					.then()
					.spec(userProfileResponseSpec)
					.extract()
					.as(UserJson.class);
		});
		step("Проверить полученные данные пользователя", () -> {
			checkUserProfile(newUser,userAfterUpdate);
		});
	}

	private void checkUserProfile(UserJson expected, UserJson actual){
		assertEquals(expected.id(), actual.id(),"Id пользователя не совпадает");
		assertEquals(expected.firstname() , actual.firstname(),"Имя пользователя не совпадает");
		assertEquals(expected.lastname(), actual.lastname(),"Отчество пользователя не совпадает");
		assertEquals(expected.username(), actual.username(),"Фамилия пользователя не совпадает");
	}

}
