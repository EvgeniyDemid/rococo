package ui.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class PaintingProfileForm {
public final SelenideElement paintingName = $x("//header[@class='card-header text-center font-bold']"),
	artistName = $x("//div[@class='text-center']"),
	editButton = $x("//button[@data-testid='edit-painting']"),
		paintingDescription =$x("//div[@class='m-4']");

}
