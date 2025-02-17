package ui.form;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class PaintingForm {
	public final SelenideElement addPainting = $x("//button[text()='Добавить картину']"),
			searchInput = $x("//input[@class='input']"),
			searchButton = $x("//button[@class='btn-icon variant-soft-surface ml-4']");
	public final ElementsCollection listPainting = $$x("//div[@class='text-center']");
}
