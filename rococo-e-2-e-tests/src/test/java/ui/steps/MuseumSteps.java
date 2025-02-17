package ui.steps;

import io.qameta.allure.Step;
import ui.page.MuseumPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MuseumSteps extends CommonSteps<MuseumSteps> {
	private final MuseumPage museumPage = new MuseumPage();

	@Step("Нажать на кнопку добавить Музей")
	public MuseumSteps clickAddMuseum() {
		museumPage.onMuseumForm().addButton.click();
		return this;
	}

	@Step("Ввести в поле поиска музей {museum}")
	public MuseumSteps setFindField(String museum) {
		museumPage.onMuseumForm().findInput.setValue(museum);
		return this;
	}

	@Step("Нажать кнопку поиска")
	public MuseumSteps clickFindButton() {
		museumPage.onMuseumForm().findButton.click();
		return this;
	}

	@Step("Перейти на страницу музея")
	public MuseumSteps goToPageMuseum(String museum) {
		museumPage.onMuseumForm().listMuseum.findBy(text(museum)).click();
		return this;
	}

	@Step("Проверить название музея {museum}")
	public MuseumSteps checkMuseumName(String museum) {
		museumPage.onMuseumProfileFrom().title.shouldHave(text(museum));
		return this;
	}

	@Step("Проверить страну музея {country}")
	public MuseumSteps checkCountry(String country) {
		museumPage.onMuseumProfileFrom().countyAndCity.shouldHave(text(country));
		return this;
	}

	@Step("Проверить город музея {city}")
	public MuseumSteps checkCity(String city) {
		museumPage.onMuseumProfileFrom().countyAndCity.shouldHave(text(city));
		return this;
	}

	@Step("Проверить описание  музея")
	public MuseumSteps checkDescription(String description) {
		museumPage.onMuseumProfileFrom().getDescription(description).shouldBe(visible);
		return this;
	}

	@Step("Нажать кнопку редактировать")
	public MuseumSteps clickEditButton() {
		museumPage.onMuseumProfileFrom().editButton.click();
		return this;
	}

	@Step("Ввести новое Изображение музея")
	public MuseumSteps setNewPainting(String photo) {
		museumPage.onMuseumEditFrom().photoInput.setValue(photo);
		return this;
	}

	@Step("Указать новое имя музея")
	public MuseumSteps setNewMuseumName(String museumName) {
		museumPage.onMuseumEditFrom().titleInput.setValue(museumName);
		return this;
	}

	@Step("Указать новую страницу музея")
	public MuseumSteps setNewCountry(String country) {
		museumPage.onMuseumEditFrom().countryList.selectByVisibleText(country);
		return this;
	}

	@Step("Указать новый город  музея")
	public MuseumSteps setNewCity(String city) {
		museumPage.onMuseumEditFrom().cityInput.setValue(city);
		return this;
	}

	@Step("Указать новое описание музея")
	public MuseumSteps setNewDescription(String description) {
		museumPage.onMuseumEditFrom().descriptionInput.setValue(description);
		return this;
	}

	@Step("Указать имя музея")
	public MuseumSteps setMuseumName(String museum) {
		museumPage.onMuseumAddFrom().titleInput.setValue(museum);
		return this;
	}
	@Step("Указать Страну музея")
	public MuseumSteps setCountry(String country) {
		museumPage.onMuseumAddFrom().countryList.selectByVisibleText(country);
		return this;
	}

	@Step("Указать Город музея")
	public MuseumSteps setCity(String city) {
		museumPage.onMuseumAddFrom().cityInput.setValue(city);
		return this;
	}
	@Step("Указать Изображение музея")
	public MuseumSteps setPhoto(String photo) {
		museumPage.onMuseumAddFrom().photoInput.setValue(photo);
		return this;
	}
	@Step("Указать о музее")
	public MuseumSteps setDescription(String description) {
		museumPage.onMuseumAddFrom().descriptionInput.setValue(description);
		return this;
	}
}
