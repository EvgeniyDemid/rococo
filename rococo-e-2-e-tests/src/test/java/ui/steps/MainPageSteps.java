package ui.steps;


import io.qameta.allure.Step;
import ui.page.MainPage;

import static com.codeborne.selenide.Condition.visible;

public class MainPageSteps extends CommonSteps<MainPageSteps> {

	MainPage mainPage = new MainPage();

	public static final String URL = CFG.frontUrl();

	@Step("Нажать кнопку войти")
	public LoginSteps clickLogin() {
		mainPage.onHeaderForm().loginButton.click();
		return new LoginSteps();
	}

	@Step("Проверить,что кнопка войти отображается ")
	public LoginSteps loginButtonIsVisible() {
		mainPage.onHeaderForm().loginButton.shouldBe(visible);
		return new LoginSteps();
	}

	@Step("Проверить, что логотип Rococo отображается ")
	public MainPageSteps checkRococoLogo() {
		mainPage.onHeaderForm().titleRococo.shouldBe(visible);
		return this;
	}

	@Step("Нажать на кнопку Художники")
	public ArtistSteps clickArtist() {
		mainPage.onHeaderForm().artistButton.click();
		return new ArtistSteps();
	}
}
