package ui.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderForm {

	public final SelenideElement
			loginButton = $x("//button[contains(text(),'Войти')]"),
			titleRococo = $x("//h1[contains(@class,'text')]");

}
