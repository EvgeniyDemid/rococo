package ui.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginForm {

	public final SelenideElement
			userNameInput = $x("//input[@name='username']"),
			passwordInput = $x("//input[@name='password']"),
			submitButton = $x("//button[@class='form__submit']"),
			registerButton = $x("//a[@href='/register']"),
			error = $x("//p[@class='form__error']"),
			showPasswordButton = $x("//button[contains(@class,'form__password-button')]");
}
