package ui.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MuseumProfileFrom {
	public final SelenideElement title = $x("//header[@class='card-header text-center font-bold']"),
			countyAndCity = $x("//div[@class='text-center']"),
			editButton = $x("//button[@data-testid='edit-museum']");

	public SelenideElement getDescription(String description) {
		return $x(String.format("//div[contains(text(),'%s')]", description));
	}
}
