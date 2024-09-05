package test;

import jupiter.annotation.TestUser;
import jupiter.extension.UserExtension;
import model.UserJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;
import static enums.UserType.RANDOM_USER;

@ExtendWith(UserExtension.class)
public class AuthorizationTest extends BaseTest {

	@BeforeEach
	void openPage() {
		open("http://127.0.0.1:3000/");
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

}
