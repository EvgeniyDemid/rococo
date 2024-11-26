package ui.form;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ArtistForm {
	public final SelenideElement addButton = $x("//button[contains(text(),'Добавить художника')]");

	public SelenideElement artist(String artist) {
		return $x(String.format("//span[contains(text(),'%s')]", artist));
	}

	public final ElementsCollection listArtist = $$x("//span[contains(@class,'flex-auto')]");
}
