package ui.form;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class PaintingEditForm {
	public final SelenideElement fileInput = $x("//input[@name='content']"),
			paintingName = $x("//input[@name='title']"),
			paintingDescription = $x("//textarea[@name='description']");

	public final ElementsCollection listAuthor = $$x("//select[@name='authorId']/option"),
			listMuseum = $$x("//select[@name='museumId']/option");
}
