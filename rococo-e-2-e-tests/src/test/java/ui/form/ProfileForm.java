package ui.form;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProfileForm {
	public final SelenideElement title = $x("//*[contains(text(),'Профиль')]"),
			exitButton = $x("//button[contains(text(),'Выйти')]"),
			login = $x("//h4[@class='text-center']"),
			choosePhotoFieldName = $x("//span[contains(text(),'Обновить фото профиля')]"),
			choosePhotoInput = $x("//input[@placeholder='Обновить фото профиля']"),
			firstnameInput = $x("//input[@name='firstname']"),
			firstnameFieldName = $x("(//input[@name='firstname']/../span)[1]"),
			surnameInput = $x("//input[@name='surname']"),
			surnameFieldName = $x("(//input[@name='surname']/../span)[1]"),
			closeButton = $x("//button[contains(text(),'Закрыть')]"),
			refreshProfileButton = $x("//button[contains(text(),'Обновить профиль')]"),
			avatar = $x("//div[@class='modal contents ']//*[contains(@class,'avatar')]"),
			popupProfileUpdated = $x("//*[contains(text(),'Профиль обновлен')]");


}
