package ui.steps;

import com.codeborne.selenide.Selenide;
import data.repository.UserRepositoryStringJdbc;
import io.qameta.allure.Step;
import model.UserJson;
import ui.page.MainPage;
import ui.page.ProfilePage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfileSteps extends CommonSteps<ProfileSteps> {

	MainPage mainPage = new MainPage();
	ProfilePage profilePage = new ProfilePage();
	private final UserRepositoryStringJdbc userRepositoryStringJdbc = new UserRepositoryStringJdbc();

	private final String logo = "rococo-e-2-e-tests/src/test/resources/logo.png";
	private final File file = new File(logo);

	public static final String URL = CFG.frontUrl();

	@Step("Нажать кнопку профиля")
	public ProfileSteps clickProfile() {
		mainPage.onHeaderForm().profileButton.click();
		return this;
	}

	@Step("Загрузить аватарку")
	public ProfileSteps addAvatar() {
		profilePage.onProfileForm().choosePhotoInput.sendKeys(file.getAbsolutePath());
		return this;
	}

	@Step("Проверить, что аватарка загружена в базу данных")
	public ProfileSteps checkAvatarInDb(UserJson userJson) {
		assertNotNull(
				userRepositoryStringJdbc.findUserInUserdataById(userJson.id()).getAvatar(),
				"У пользователя " + userJson.username() + " не загружена аватарка ");
		return this;
	}

	@Step("Нажать на кнопку 'Обновить профиль'")
	public ProfileSteps clickRefreshProfile() {
		profilePage.onProfileForm().refreshProfileButton.click();
		return this;
	}

	@Step("Проверить заголовок страницы")
	public ProfileSteps checkTitle() {
		profilePage.onProfileForm().title.shouldBe(visible);
		return this;
	}

	@Step("Нажать кнопку выхода")
	public MainPageSteps clickExit() {
		profilePage.onProfileForm().exitButton.click();
		return new MainPageSteps();
	}

	@Step("Проверить логин {login} пользователя")
	public ProfileSteps checkLogin(String login) {
		profilePage.onProfileForm().login.shouldHave(text("@" + login));
		return this;
	}

	@Step("Проверить название поля добавить фото")
	public ProfileSteps checkChoosePhoto() {
		profilePage.onProfileForm().choosePhotoFieldName.shouldBe(visible);
		return this;
	}

	@Step("Указать имя {firstname} пользователя ")
	public ProfileSteps setFirstname(String firstname) {
		profilePage.onProfileForm().firstnameInput.setValue(firstname);
		return this;
	}

	@Step("Указать Фамилию  {surname} пользователя ")
	public ProfileSteps setSurname(String surname) {
		profilePage.onProfileForm().surnameInput.setValue(surname);
		return this;
	}

	@Step("Нажать кнопку закрыть")
	public ProfileSteps clickClose() {
		profilePage.onProfileForm().closeButton.click();
		return this;
	}

	@Step("Проверить, что аватар отображается")
	public ProfileSteps checkAvatar() {
		profilePage.onProfileForm().avatar.shouldBe(visible);
		return this;
	}

	@Step("Проверить название поля Имя")
	public ProfileSteps checkFirstnameFieldName() {
		profilePage.onProfileForm().firstnameFieldName.shouldHave(text("Имя"));
		return this;
	}

	@Step("Проверить, что отображается сообщение 'Профиль обновлен'")
	public ProfileSteps popupProfileUpdatedIsVisible() {
		profilePage.onProfileForm().popupProfileUpdated.shouldBe(visible);
		return this;
	}

	@Step(" Проверить название поля Фамилия")
	public ProfileSteps checkSurnameFieldName() {
		profilePage.onProfileForm().surnameFieldName.shouldHave(text("Фамилия"));
		return this;
	}

	@Step("Проверить имя {userName} в базе данных")
	public ProfileSteps checkUserNameInDb(UserJson userJson,String userName) {
		assertEquals(
				userName,
				userRepositoryStringJdbc.findUserInUserdataById(userJson.id()).getFirstname(),
				"Имена пользователя не совпадают");
		return this;
	}
	@Step("Проверить имя {surname} в базе данных")
	public ProfileSteps checkSurnameInDb(UserJson userJson,String surname) {
		assertEquals(
				surname,
				userRepositoryStringJdbc.findUserInUserdataById(userJson.id()).getLastname(),
				"Фамилии пользователя не совпадают");
		return this;
	}
	@Step("Проверить имя {userName} в базе данных")
	public ProfileSteps checkUserNameInDb(UserJson userJson) {
		assertEquals(
				userJson.username(),
				userRepositoryStringJdbc.findUserInUserdataById(userJson.id()).getUsername(),
				"Имена пользователя не совпадают");
		return this;
	}
	@Step("Проверить имя {surname} в базе данных")
	public ProfileSteps checkSurnameInDb(UserJson userJson) {
		assertEquals(
				userJson.lastname(),
				userRepositoryStringJdbc.findUserInUserdataById(userJson.id()).getLastname(),
				"Фамилии пользователя не совпадают");
		return this;
	}
}
