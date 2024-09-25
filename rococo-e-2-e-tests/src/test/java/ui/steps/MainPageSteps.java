package ui.steps;


import io.qameta.allure.Step;
import ui.page.MainPage;

import static com.codeborne.selenide.Condition.visible;

public class MainPageSteps {

	MainPage mainPage = new MainPage();

	@Step("Нажать кнопку войти")
	public LoginSteps clickLogin() {
		mainPage.onHeaderForm().loginButton.click();
		return new LoginSteps();
	}

	@Step("Проверить, что логотип Rococo отображается ")
	public MainPage checkRococoLogo(){
	mainPage.onHeaderForm().titleRococo.shouldBe(visible);
	return new MainPage();
	}
}
