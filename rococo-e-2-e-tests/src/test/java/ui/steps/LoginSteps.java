package ui.steps;


import io.qameta.allure.Step;
import ui.page.LoginPage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;

public class LoginSteps {

	LoginPage loginPage = new LoginPage();

	@Step("Нажать на кнопку Зарегистрироваться")
	public RegisterSteps clickRegister() {
		loginPage.onLoginForm().registerButton.click();
		return new RegisterSteps();
	}

	@Step("Ввести имя пользователя {username}")
	public LoginSteps setUserName(String username) {
		loginPage.onLoginForm().userNameInput.setValue(username);
		return this;
	}

	@Step("Ввести пароль пользователя {password}")
	public LoginSteps setPassword(String password) {
		loginPage.onLoginForm().passwordInput.setValue(password);
		return this;
	}

	@Step("Нажать на кнопку войти")
	public MainPageSteps clickSubmit() {
		loginPage.onLoginForm().submitButton.click();
		return new MainPageSteps();
	}

	@Step("Проверить текст ошибки  '{error}'")
	public LoginSteps checkErrorText(String error) {
		loginPage.onLoginForm().error.shouldHave(text(error));
		return this;
	}

	@Step("Нажать на кнопку показать пароль")
	public LoginSteps clickShowPassword() {
		loginPage.onLoginForm().showPasswordButton.click();
		return this;
	}

	@Step("Проверить, что пароль экранирован")
	public LoginSteps checkPasswordShielded() {
		loginPage.onLoginForm().passwordInput.shouldHave(attribute("type", "password"));
		return this;
	}
	@Step("Проверить, что пароль отображается")
	public LoginSteps checkPasswordShow() {
		loginPage.onLoginForm().passwordInput.shouldHave(attribute("type", "text"));
		return this;
	}

	@Step("Проверить название поля {fieldName} ")
	public LoginSteps checkNameField(String fieldName) {
		loginPage.onLoginForm().userNameInput.preceding(0).shouldHave(text(fieldName));
		return this;
	}
}
