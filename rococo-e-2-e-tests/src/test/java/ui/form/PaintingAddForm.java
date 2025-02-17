package ui.form;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$x;

public class PaintingAddForm {
	public final SelenideElement namePainting = $x("//input[@name='title']"),
			inputFile = $x("//input[@name='content']"),
			inputDescription = $x("//textarea[@name='description']");

	public final Select listAuthor = new Select($x("//select[@name='authorId']")),
			listMuseum = new Select($x("//select[@name='museumId']"));
}

