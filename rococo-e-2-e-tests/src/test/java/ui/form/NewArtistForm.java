package ui.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class NewArtistForm {
	public final SelenideElement title = $x("//div[@class='modal contents ']//header"),
			description = $x("//div[@class='modal contents ']//article"),
			fieldName = $x("(//input[@name='name']//../span)[1]"),
			fieldNameInput = $x("//input[@name='name']"),
			nameFieldArtistImage = $x("(//input[@name='photo']//../span)[1]"),
			fieldArtistImageInput = $x("//input[@name='photo']"),
			fieldBiography = $x("(//textarea[@name='biography']//../span)[1]"),
			fieldBiographyInput = $x("//textarea[@name='biography']"),
			errorName = $x("(//span[@class='text-error-400'])[1]"),
			errorBiography = $x("(//span[@class='text-error-400'])[3]");



}
