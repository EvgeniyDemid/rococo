package ui.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderForm {

	public final SelenideElement
			loginButton = $x("//button[contains(text(),'Войти')]"),
			titleRococo = $x("//h1[contains(@class,'text')]"),
			profileButton = $x("//button[@class='btn-icon variant-filled-surface relative']"),
			paintButton = $x("//*[@id='shell-header']//a[@href='/painting']"),
			artistButton = $x("//*[@id='shell-header']//a[@href='/artist']"),
			museumButton = $x("//*[@id='shell-header']//a[@href='/museum']");
}

