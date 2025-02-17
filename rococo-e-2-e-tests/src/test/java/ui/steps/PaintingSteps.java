package ui.steps;

import io.qameta.allure.Step;
import ui.page.PaintingPage;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;

public class PaintingSteps extends CommonSteps<PaintingSteps> {

	private final PaintingPage paintingPage = new PaintingPage();

	@Step("Ввести в поле поиска название картины")
	public PaintingSteps setPaintingInSearchField(String painting) {
		paintingPage.onPaintingForm().searchInput.setValue(painting);
		return this;
	}

	@Step("Нажать на кнопку поиска")
	public PaintingSteps clickSearchButton() {
		paintingPage.onPaintingForm().searchButton.click();
		return this;
	}

	@Step("Проверить, что картинка есть в списке")
	public PaintingSteps checkPaintingIsVisible(String painting) {
		paintingPage.onPaintingForm().listPainting.shouldHave(texts(painting));
		return this;
	}

	@Step("Перейти на страницу картины")
	public PaintingSteps goToPaintingPage(String painting) {
		setPaintingInSearchField(painting).clickSearchButton();
		paintingPage.onPaintingForm().
				listPainting.findBy(text(painting)).
				click();
		return this;
	}

	@Step("Нажать кнопку 'Добавит Картину'")
	public PaintingSteps clickAddPainting() {
		paintingPage.onPaintingForm().addPainting.click();
		return this;
	}

	@Step("Указать название картины { painting}")
	public PaintingSteps setPaintingName(String painting) {
		paintingPage.onPaintingAddForm().namePainting.setValue(painting);
		return this;
	}

	@Step("Загрузить изображение картины")
	public PaintingSteps setPaintingImage(String file) {
		paintingPage.onPaintingAddForm().inputFile.setValue(file);
		return this;
	}

	@Step("Указать автора картины")
	public PaintingSteps selectArtist(String artist) {
		paintingPage.onPaintingAddForm().listAuthor.selectByVisibleText(artist);
		return this;
	}

	@Step("Указать описание картины")
	public PaintingSteps setDescription(String description) {
		paintingPage.onPaintingAddForm().inputDescription.scrollTo().setValue(description);
		return this;
	}

	@Step("Указать, где хранится оригинал")
	public PaintingSteps selectMuseum(String museum) {
		paintingPage.onPaintingAddForm().listMuseum.selectByVisibleText(museum);
		return this;
	}

	@Step("Проверить имя картины")
	public PaintingSteps checkPaintingName(String painting) {
		paintingPage.onPaintingProfileForm().paintingName.shouldHave(text(painting));
		return this;
	}

	@Step("Проверить художника картины")
	public PaintingSteps checkPaintingArtist(String artist) {
		paintingPage.onPaintingProfileForm().artistName.shouldHave(text(artist));
		return this;
	}

	@Step("Проверить описание картины")
	public PaintingSteps checkPaintingDescription(String Description) {
		paintingPage.onPaintingProfileForm().paintingDescription.shouldHave(text(Description));
		return this;
	}

	@Step("Нажать на кнопку редактировать")
	public PaintingSteps clickEdit() {
		paintingPage.onPaintingProfileForm().editButton.click();
		return this;
	}
}
