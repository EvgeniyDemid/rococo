package ui.steps;

import io.qameta.allure.Step;
import ui.page.ArtistPage;
import ui.page.MainPage;

import static com.codeborne.selenide.CollectionCondition.texts;
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
	public ArtistSteps setArtistImage(String image){
		artistPage.onNewArtistForm().fieldArtistImageInput.setValue(image);
		return this;
	}
	@Step("Проверить сообщение о добалении художника  {artist}")
	public MainPage checkAlertAddNewArtist(String artist){
	commonElements.alert.shouldHave(text("Добавлен художник: "+artist));
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
	@Step("Перейти в профиль артиста {artist} ")
	public ArtistSteps goToEditForm(String artist){
		artistPage.
				onArtistPage().
				artist(artist).
				click();
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
	@Step("Обновить изображение художника")
	public ArtistSteps editPhoto(String photo){
	artistPage.onArtistEditForm().inputPhoto.setValue(photo);
	return this;
	}
	@Step("Обновить имя художника на {name}")
	public ArtistSteps editName(String name){
	artistPage.onArtistEditForm().inputName.setValue(name);
	return this;
	}
	@Step("Обновить биографию художника")
	public ArtistSteps editBiography(String biography){
	artistPage.onArtistEditForm().inputBiography.setValue(biography);
	return this;
	}
	@Step("Проверить, что заголовок окна редактирования художника отображается")
	public ArtistSteps titleEditFormIsVisible(){
		artistPage.onArtistEditForm().title.shouldBe(visible);
		return this;
	}
	@Step("Проверить сообщение, что пользователь обновлен")
	public ArtistSteps checkAlertEditArtist(String artist){
		commonElements.alert.shouldHave(text("Обновлен художник: "+artist));
		return this;
	}
	@Step("Проверить имя {artist} художника")
	public ArtistSteps checkArtistName(String artist){
		artistPage.onArtistProfileForm().name.shouldHave(text(artist));
		return this;
	}
	@Step("Проверить биографию художника")
	public ArtistSteps checkArtistBiography(String biography){
	artistPage.onArtistProfileForm().biography.shouldHave(text(biography));
	return this;
	}
	@Step("Проверить, что аватар отображается")
	public ArtistSteps checkAvatar(){
	artistPage.onArtistProfileForm().avtar.shouldBe(visible);
	return this;
	}
	@Step("Нажать кнопку редактирования художника")
	public ArtistSteps clickEditButton(){
	artistPage.onArtistProfileForm().editButton.click();
	return this;
	}

	@Step("Нажать добавить картину")
	public ArtistSteps clickAddPainting(){
	artistPage.onArtistProfileForm().addPainting.click();
	return this;
	}
	@Step("Ввести название картины { painting}")
	public ArtistSteps setPaintingName(String painting){
	artistPage.onArtistAddPaintingForm().inputTitle.setValue(painting);
	return this;
	}
	@Step("Загрузите изображение картины")
	public ArtistSteps setPaintingImage(String file){
		artistPage.onArtistAddPaintingForm().inputPainting.setValue(file);
		return this;
	}
	@Step("Указать описание картины")
	public ArtistSteps setPaintingDescription(String description){
		artistPage.onArtistAddPaintingForm().inputDescription.setValue(description);
		return this;
	}
	@Step("Выбрать музей {museum} при добавлении картины ")
	public ArtistSteps selectMuseum(String museum){
	artistPage.onArtistAddPaintingForm().listMuseum.findBy(text(museum)).scrollTo().click();
	return this;
	}
	@Step("Проверить, список картин содержит картину  { painting}")
	public ArtistSteps checkPictInList(String painting){
	artistPage.onArtistProfileForm().listPainting.shouldHave(texts(painting));
	return this;
	}
}
