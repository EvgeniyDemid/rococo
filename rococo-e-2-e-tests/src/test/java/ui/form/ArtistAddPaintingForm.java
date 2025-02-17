package ui.form;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ArtistAddPaintingForm {
	public final SelenideElement inputTitle = $x("//input[@name='title']"),
			inputPainting = $x("//input[@name='content']"),
			inputDescription = $x("//textarea[@name='description']");
	public final ElementsCollection listMuseum = $$x("//select[@class='select']/option");
}
