package ui.steps;

import io.qameta.allure.Step;
import ui.page.ArtistPage;
import ui.page.MainPage;

import static com.codeborne.selenide.Condition.*;
import static enums.Artist.*;

public class ArtistSteps extends CommonSteps<ArtistSteps> {

	ArtistPage artistPage = new ArtistPage();

	@Step("Проверить название полей формы добавления художника ")
	public void checkAddArtist() {
		artistPage.onNewArtistForm().title.shouldHave(text(TITLE_ADD_FORM.getValue()));
		artistPage.onNewArtistForm().description.shouldHave(text(DESCRIPTION_ADD_FORM.getValue()));
		artistPage.onNewArtistForm().fieldName.shouldHave(text(NAME_ADD_FORM.getValue()));
		artistPage.onNewArtistForm().fieldNameInput.shouldHave(
				attribute("placeholder", NAME_ADD_FORM_PLACEHOLDER.getValue()));
		artistPage.onNewArtistForm().nameFieldArtistImage.shouldHave(text(ARTIST_IMAGE_ADD_FORM.getValue()));
		artistPage.onNewArtistForm().fieldBiography.shouldHave(text(BIOGRAPHY_ADD_FORM.getValue()));
		artistPage.onNewArtistForm().fieldBiographyInput.shouldHave(attribute(
				"placeholder", BIOGRAPHY_ADD_FORM_PLACEHOLDER.getValue()));
	}

	@Step("Нажать кнопку 'Добавить Художника'")
	public ArtistSteps clickAddArtist() {
		artistPage.onArtistPage().addButton.click();
		return this;
	}

	@Step("Проверить текст ошибка поля имя при добавлении художника")
	public ArtistSteps checkErrorNameOnNewArtist(String error){
	artistPage.onNewArtistForm().errorName.shouldHave(text(error));
	return this;
	}
	@Step("Проверить текст ошибка поля биография при добавлении художника")
	public ArtistSteps checkErrorBiographyOnNewArtist(String error){
		artistPage.onNewArtistForm().errorBiography.shouldHave(text(error));
		return this;
	}
	@Step("Указать имя ")
	public ArtistSteps setArtistName(String name){
	artistPage.onNewArtistForm().fieldNameInput.setValue(name);
	return this;
	}
	@Step("Указать биографию ")
	public ArtistSteps setBiography(String biography){
	artistPage.onNewArtistForm().fieldBiographyInput.setValue(biography);
	return this;
	}
	@Step("Указать фото ")
	public ArtistSteps setImage(String image){
		artistPage.onNewArtistForm().fieldArtistImageInput.setValue(image);
		return this;
	}
	@Step("Проверить сообщение о добалении художника  {artist}")
	public MainPage checkAlertAddNewArtist(String artist){
	artistPage.onNewArtistForm().alertNewArtistAdd.shouldHave(text("Добавлен художник: "+artist));
	return new MainPage();
	}
	@Step("Проверить, что артист: {artist} есть в списке ")
	public ArtistSteps checkArtistInList(String artist){
		artistPage.
				onArtistPage().
				artist(artist).
				shouldBe(visible);
	return this;
	}
	@Step("Проверить, что артист: {artist} нет в списке ")
	public ArtistSteps checkArtistInListNoExit(String artist){
		artistPage.
				onArtistPage().
				listArtist.
				findBy(text(artist)).
				shouldNotBe(visible);
		return this;
	}
}
