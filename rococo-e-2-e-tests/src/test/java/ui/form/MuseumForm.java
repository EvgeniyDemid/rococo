package ui.form;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MuseumForm {
	public final SelenideElement findInput = $x("//input[@class='input']"),
			findButton = $x("//button[@class='btn-icon variant-soft-surface ml-4']"),
			addButton = $x("//button[contains(text(),'Добавить музей')]");

	public final ElementsCollection listMuseum = $$x("//div[@class='w-100']/ul/li");

}
