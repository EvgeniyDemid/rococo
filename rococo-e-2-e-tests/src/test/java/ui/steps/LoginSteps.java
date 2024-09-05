package ui.steps;


import io.qameta.allure.Step;
import ui.page.LoginPage;

public class LoginSteps {

	LoginPage loginPage = new LoginPage();

	@Step("Нажать на кнопку Зарегистрироваться")
	public RegisterSteps clickRegister() {
		loginPage.onLoginForm().registerButton.click();
		return new RegisterSteps();
	}
}
