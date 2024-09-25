package tests.web;

import enums.Fields;
import jupiter.annotation.TestUser;
import model.UserJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static enums.Errors.INVALID_CREDENTIALS;
import static enums.UserType.RANDOM_USER;
import static enums.UserType.REGISTERED;

public class AuthorizationWebTest extends BaseWebTest {

	@BeforeEach
	void openPage() {
		open(CFG.frontUrl());
	}

	@TestUser(USER_TYPE = RANDOM_USER)
	@Test
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

	@TestUser(USER_TYPE = REGISTERED)
	@Test
	public void loginSuccessful(UserJson userJson) {
		mainPageSteps.
				clickLogin().
				setUserName(userJson.username()).
				setPassword(userJson.password()).
				clickSubmit().
				checkRococoLogo();

	}

	@TestUser(USER_TYPE = RANDOM_USER)
	@Test
	public void loginUnsuccessful(UserJson userJson) {
		mainPageSteps.
				clickLogin().
				setUserName(userJson.username()).
				setPassword(userJson.password()).
				clickSubmit();
		loginSteps.checkErrorText(INVALID_CREDENTIALS.getValue());
	}

	@TestUser(USER_TYPE = RANDOM_USER)
	@Test
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
	public void checkLoginForm() {
		mainPageSteps.
				clickLogin().
				checkNameField(Fields.USER_NAME.getName());
	}
}
