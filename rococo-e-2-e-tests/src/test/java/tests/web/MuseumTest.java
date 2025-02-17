package tests.web;

import jdk.jfr.Description;
import jupiter.annotation.ApiLogin;
import jupiter.annotation.TestMuseum;
import jupiter.annotation.TestUser;
import jupiter.extension.MuseumExtension;
import model.MuseumJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static enums.UserType.RANDOM_REGISTERED_USER;

@ExtendWith({
		MuseumExtension.class})
@DisplayName("Музеи")
public class MuseumTest extends BaseWebTest {

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestMuseum(crateInDb = false)
	@DisplayName("Добавить новый музей")
	public void addNewMuseum(MuseumJson museumJson) {
		mainPageSteps.
				clickMuseum().
				clickAddMuseum().
				setMuseumName(museumJson.getTitle()).
				setCountry(museumJson.getGeo().getCountry().getName()).
				setCity(museumJson.getGeo().getCity()).
				setPhoto(museumJson.getPhoto()).
				setDescription(museumJson.getDescription()).
				clickSubmitButton().
				checkAlert("Добавлен музей: " + museumJson.getTitle());
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestMuseum
	@DisplayName("Поиск в музея")
	public void findMuseum(MuseumJson museumJson) {
		mainPageSteps.
				clickMuseum().
				setFindField(museumJson.getTitle()).clickFindButton().
				goToPageMuseum(museumJson.getTitle())
				.checkMuseumName(museumJson.getTitle());
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestMuseum
	@DisplayName("Проверка  музея после создания ")
	public void checkMuseumAfterCreate(MuseumJson museumJson) {
		mainPageSteps.
				clickMuseum().
				goToPageMuseum(museumJson.getTitle()).
				checkMuseumName(museumJson.getTitle()).
				checkCity(museumJson.getGeo().getCity()).
				checkCountry(museumJson.getGeo().getCountry().getName()).
				checkDescription(museumJson.getDescription());
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestMuseum
	@DisplayName("Изменить имя музея ")
	public void editMuseumName(MuseumJson museumJson) {
		String newName = museumJson.getTitle() + "new";
		mainPageSteps.
				clickMuseum().
				goToPageMuseum(museumJson.getTitle()).
				clickEditButton().
				setNewMuseumName(newName).
				setPhoto(museumJson.getPhoto()).
				clickSubmitButton().
				checkAlert("Обновлен музей: " + newName);
	}
	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestMuseum
	@DisplayName("Изменить город музея")
	public void editCityMuseum(MuseumJson museumJson) {
		String newCity = museumJson.getGeo().getCity() + "new";
		mainPageSteps.
				clickMuseum().
				goToPageMuseum(museumJson.getTitle()).
				clickEditButton().
				setNewCity(newCity).
				setPhoto(museumJson.getPhoto()).
				clickSubmitButton().
				checkAlert("Обновлен музей: " + museumJson.getTitle()).
				checkCity(newCity);
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestMuseum
	@DisplayName("Изменить описание  музея")
	public void editDescriptionMuseum(MuseumJson museumJson) {
		String newCity = museumJson.getDescription() + "new";
		mainPageSteps.
				clickMuseum().
				goToPageMuseum(museumJson.getTitle()).
				clickEditButton().
				setNewDescription(newCity).
				setPhoto(museumJson.getPhoto()).
				clickSubmitButton().
				checkAlert("Обновлен музей: " + museumJson.getTitle()).
				checkDescription(newCity);
	}
}
