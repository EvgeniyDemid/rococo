package tests.web;

import jupiter.annotation.ApiLogin;
import jupiter.annotation.TestUser;
import model.UserJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static enums.UserType.RANDOM_REGISTERED_USER;

@DisplayName("Профиль пользователя")
public class ProfileTest extends BaseWebTest {

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Добавить логотип")
	public void addLogo(UserJson userJson) {
		profileSteps.
				clickProfile().
				addAvatar().
				clickRefreshProfile().
				popupProfileUpdatedIsVisible().
				checkAvatarInDb(userJson);

	}

	@Test
	@ApiLogin()
	@TestUser(username = "petrov",password = "12345")
	@DisplayName("Проверка профиля пользователя")
	public void checkProfileForm(UserJson userJson) {
		profileSteps.
				clickProfile().
				checkTitle().
				checkAvatar().
				checkLogin(userJson.username()).
				checkChoosePhoto().
				checkFirstnameFieldName().
				checkSurnameFieldName();

	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Изменить имя пользователя")
	public void setNewUserName(UserJson userJson) {
		String newUsername = "newName";
		profileSteps.
				clickProfile().
				setFirstname(newUsername).
				clickRefreshProfile().
				popupProfileUpdatedIsVisible().
				checkUserNameInDb(userJson, newUsername);

	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Изменить фамилию пользователя")
	public void setNewSurname(UserJson userJson) {
		String newSurname = "newName";
		profileSteps.
				clickProfile().
				setSurname(newSurname).
				clickRefreshProfile().
				popupProfileUpdatedIsVisible().
				checkSurnameInDb(userJson, newSurname);

	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName(" Изменение без сохранения")
	public void closeWithoutSave(UserJson userJson) {
		String name = "newName";
		profileSteps.
				clickProfile().
				setSurname(name).
				setFirstname(name).
				clickClose().
				checkSurnameInDb(userJson).
				checkUserNameInDb(userJson);
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Выход")
	public void logOut() {
		profileSteps.
				clickProfile().
				clickExit().
				loginButtonIsVisible();
	}
}
