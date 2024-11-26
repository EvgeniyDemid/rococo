package ui.steps;

import io.qameta.allure.Step;
import ui.page.RegisterPage;

import static com.codeborne.selenide.Condition.visible;

public class RegisterSteps extends CommonSteps<MainPageSteps> {

	RegisterPage registerPage = new RegisterPage();

	@Step("Ввести имя пользователя {username}")
	public RegisterSteps setUserName(String username) {
		registerPage.onRegisterForm().userNameInput.setValue(username);
		return this;
	}

	@Step("Ввести пароль пользователя ")
	public RegisterSteps setPassword(String password) {
		registerPage.onRegisterForm().passwordInput.setValue(password);
		return this;
	}

	@Step("Повторить ввод пароля пользователя ")
	public RegisterSteps setPasswordRepeat(String password) {
		registerPage.onRegisterForm().submitPassword.setValue(password);
		return this;
	}

	@Step("Нажать на кнопку 'Зарегистрироваться' ")
	public RegisterSteps clickRegister() {
		registerPage.onRegisterForm().registerButton.click();
		return this;
	}

	@Step("Проверить, что отображается приветственное окно")
	public RegisterSteps checkWelcome() {
		registerPage.onRegisterForm().welcomeTitle.shouldBe(visible);
		return this;
	}
}
