package ui.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class RegisterForm {

	public final SelenideElement
			userNameInput = $x("//input[@id='username']"),
			passwordInput = $x("//input[@id='password']"),
			submitPassword = $x("//input[@id='passwordSubmit']"),
			registerButton = $x("//button[@class='form__submit']"),
			welcomeTitle = $x("//p[contains(text(),'Добро пожаловать')]");

}
