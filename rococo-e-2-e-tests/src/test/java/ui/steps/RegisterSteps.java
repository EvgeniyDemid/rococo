package ui.steps;

import io.qameta.allure.Step;
import ui.page.RegisterPage;

import static com.codeborne.selenide.Condition.visible;

public class RegisterSteps {

	RegisterPage registerPage = new RegisterPage();

	@Step("Ввести имя пользователя {username}")
	public RegisterSteps setUserName(String userName){
	registerPage.onRegisterForm().userNameInput.setValue(userName);
	return new RegisterSteps();
	}

	@Step("Ввести пароль пользователя ")
	public RegisterSteps setPassword(String password){
		registerPage.onRegisterForm().passwordInput.setValue(password);
		return new RegisterSteps();
	}

	@Step("Повторить ввод пароля пользователя ")
	public RegisterSteps setPasswordRepeat(String password){
		registerPage.onRegisterForm().submitPassword.setValue(password);
		return new RegisterSteps();
	}
	@Step("Нажать на кнопку 'Зарегистрироваться' ")
	public RegisterSteps clickRegister(){
		registerPage.onRegisterForm().registerButton.click();
		return new RegisterSteps();
	}

	@Step("Проверить, что отображается приветственное окно")
	public RegisterSteps checkWelcome(){
	registerPage.onRegisterForm().welcomeTitle.shouldBe(visible);
	return new RegisterSteps();
	}

}
