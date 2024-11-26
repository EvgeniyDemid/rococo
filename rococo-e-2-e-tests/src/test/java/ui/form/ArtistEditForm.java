package ui.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ArtistEditForm {
	public final SelenideElement name = $x("//header[contains(@class,'card-header')]"),
			biography = $x("//p[contains(@class,'col-span')]"),
			avtar = $x("//img[contains(@class,'avatar')]"),
			editButton = $x("//button[contains(@data-testid,'edit-artist')]"),
			addAvatar = $x("(//button[contains(@class,'btn variant-filled-primary')])[1]"),
			addPainting = $x("(//button[contains(@class,'btn variant-filled-primary')])[2]");
}
