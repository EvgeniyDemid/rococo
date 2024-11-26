package ui.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CommonElements {

	public final SelenideElement error = $x("//*[@class='form__error']"),
			searchInput = $x("//input[@type='search']"),
			searchButton = $x("//input[@type='search']/../button"),
			closeButton = $x("//button[contains(text(),'Закрыть')]"),
			addButton = $x("//button[@type='submit']");

}
