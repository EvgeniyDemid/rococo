package ui.steps;


import io.qameta.allure.Step;
import ui.page.MainPage;

public class MainPageSteps {

	MainPage mainPage = new MainPage();

	@Step("Нажать кнопку войти")
	public LoginSteps clickLogin() {
		mainPage.onHeaderForm().loginButton.click();
		return new LoginSteps();
	}
}
