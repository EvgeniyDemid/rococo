package ui.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ArtistEditForm {
	public final SelenideElement title = $x("public final SelenideElement addButton "),
	inputPhoto=$x("//input[@name='photo']"),
	inputName=$x("//input[@name='name']"),
	inputBiography = $x("//textarea[@name='biography']");

}
