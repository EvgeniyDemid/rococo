package ui.form;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$x;

public class MuseumEditFrom {
	public final SelenideElement photoInput = $x("//input[@name='photo']"),
			titleInput = $x("//input[@name='title']"),
			cityInput = $x("//input[@name='city']"),
			descriptionInput = $x("//textarea[@name='description']");

	public final Select countryList = new Select($x("//select[@name='countryId']"));

}
