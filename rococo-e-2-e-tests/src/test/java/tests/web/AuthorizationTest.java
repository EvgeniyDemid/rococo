package tests.web;

import jupiter.annotation.TestUser;
import model.UserJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.steps.MainPageSteps;

import static com.codeborne.selenide.Selenide.open;
import static enums.Errors.*;
import static enums.Fields.PASSWORD;
import static enums.Fields.USER_NAME;
import static enums.UserType.*;

public class AuthorizationTest extends BaseWebTest {

	@BeforeEach
	void openPage() {
		open(MainPageSteps.URL);
	}

	@TestUser(USER_TYPE = RANDOM_NO_REGISTERED_USER)
	@Test
	@DisplayName("Успешная регистрация пользователя")
	public void registrationSuccessful(UserJson userJson) {
		mainPageSteps.
				clickLogin().
				clickRegister().
				setUserName(userJson.username()).
				setPassword(userJson.password()).
				setPasswordRepeat(userJson.password()).
				clickRegister().
				checkWelcome();

	}

	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@Test
	@DisplayName("Регистрация уже созданного пользователя")
	public void registrationWithUsernameExists(UserJson userJson) {
		mainPageSteps.
				clickLogin().
				clickRegister().
				setUserName(userJson.username()).
				setPassword(userJson.password()).
				setPasswordRepeat(userJson.password()).
				clickRegister().
				checkErrorText(USERNAME_EXISTS.getValue(userJson.username()));

	}

	@TestUser(USER_TYPE = RANDOM_NO_REGISTERED_USER)
	@Test
	@DisplayName("Проверка минимальной длинный пароля")
	public void checkMinLengthPassword(UserJson userJson) {
		mainPageSteps.
				clickLogin().
				clickRegister().
				setUserName(userJson.username()).
				setPassword("12").
				setPasswordRepeat("12").
				clickRegister().
				checkErrorText(PASSWORD_LENGTH.getValue());

	}

	@TestUser(USER_TYPE = RANDOM_NO_REGISTERED_USER)
	@Test
	@DisplayName("Проверка максимальной длинны пароля")
	public void checkMaxLengthPassword(UserJson userJson) {
		mainPageSteps.
				clickLogin().
				clickRegister().
				setUserName(userJson.username()).
				setPassword("1234567890123").
				setPasswordRepeat("1234567890123").
				clickRegister().
				checkErrorText(PASSWORD_LENGTH.getValue());

	}

	@TestUser(USER_TYPE = RANDOM_NO_REGISTERED_USER)
	@Test
	@DisplayName("Отображение ошибки при не совпадении паролей")
	public void checkErrorWherePasswordNotEqual(UserJson userJson) {
		mainPageSteps.
				clickLogin().
				clickRegister().
				setUserName(userJson.username()).
				setPassword("12346").
				setPasswordRepeat("1234567").
				clickRegister().
				checkErrorText(PASSWORDS_SHOULD_BE_EQUAL.getValue());

	}

	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@Test
	@DisplayName("Успешная авторизация пользователя")
	public void loginSuccessful(UserJson userJson) {
		mainPageSteps.
				clickLogin().
				setUserName(userJson.username()).
				setPassword(userJson.password()).
				clickSubmit().
				checkRococoLogo();

	}

	@TestUser(USER_TYPE = RANDOM_NO_REGISTERED_USER)
	@Test
	@DisplayName("Не успешная авторизация пользователя")
	public void loginUnsuccessful(UserJson userJson) {
		mainPageSteps.
				clickLogin().
				setUserName(userJson.username()).
				setPassword(userJson.password()).
				clickSubmit().
				checkErrorText(INVALID_CREDENTIALS.getValue());
	}

	@TestUser(USER_TYPE = RANDOM_NO_REGISTERED_USER)
	@Test
	@DisplayName("Отображение пароля")
	public void checkPasswordShow(UserJson userJson) {
		mainPageSteps.
				clickLogin().
				setUserName(userJson.username()).
				setPassword(userJson.password()).
				checkPasswordShielded().
				clickShowPassword().
				checkPasswordShow();
	}

	@Test
	@DisplayName("Проверка логин формы ")
	public void checkLoginForm() {
		mainPageSteps.
				clickLogin().
				checkNameField(USER_NAME.getName()).
				checkPlaceholderNameField(USER_NAME.getPlaceholder()).
				checkPasswordField(PASSWORD.getName()).
				checkPlaceholderPasswordField(PASSWORD.getPlaceholder());
	}
}
