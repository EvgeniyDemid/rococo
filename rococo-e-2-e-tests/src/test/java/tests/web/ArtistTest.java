package tests.web;

import jdk.jfr.Description;
import jupiter.annotation.*;
import jupiter.extension.ArtistExtension;
import jupiter.extension.MuseumExtension;
import jupiter.extension.PaintingExtension;
import model.ArtistJson;
import model.MuseumJson;
import model.PaintingJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static enums.Errors.BIOGRAPHY_MIN_SYMBOL;
import static enums.Errors.NAME_MIN_SYMBOL;
import static enums.UserType.RANDOM_REGISTERED_USER;

@ExtendWith({
		ArtistExtension.class,
		PaintingExtension.class,
		MuseumExtension.class})
@DisplayName("Артисты")
public class ArtistTest extends BaseWebTest {


	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Проверка полей формы добавления художника")
	public void checkAddForm() {
		mainPageSteps.
				clickArtist().
				clickAddArtist().
				checkAddArtist();
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Валидация полей формы добавления художника")
	public void validationFieldAddForm() {
		mainPageSteps.
				clickArtist().
				clickAddArtist().
				setArtistName("1").
				setArtistImage(new ArtistJson().random().getPhoto()).
				setBiography("1").
				clickSubmitButton().
				checkErrorNameOnNewArtist(NAME_MIN_SYMBOL.getValue())
				.checkErrorBiographyOnNewArtist(BIOGRAPHY_MIN_SYMBOL.getValue());

	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Добавление художника")
	public void addArtist() {
		ArtistJson artistJson = new ArtistJson().random();
		mainPageSteps.
				clickArtist().
				clickAddArtist().
				setArtistName(artistJson.getName()).
				setArtistImage(new ArtistJson().random().getPhoto()).
				setBiography(artistJson.getBiography()).
				clickSubmitButton().
				checkAlertAddNewArtist(artistJson.getName());
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestArtist
	@DisplayName("Поиск художника")
	public void findArtist(ArtistJson artistJson) {
		mainPageSteps.
				clickArtist().
				setNameInFindField(artistJson.getName()).
				clickFind().
				checkArtistInList(artistJson.getName());
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Отмена добавления художника")
	public void cancelAddArtist() {
		ArtistJson artistJson = new ArtistJson().random();
		mainPageSteps.
				clickArtist().
				clickAddArtist().
				setArtistName(artistJson.getName()).
				setArtistImage(new ArtistJson().random().getPhoto()).
				setBiography(artistJson.getBiography()).
				clickCloseButton().
				checkArtistInListNoExit(artistJson.getName());
	}


	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestArtist
	@DisplayName("Изменить имя в профиле художника")
	public void editNameInProfile(ArtistJson artistJson) {
		String newName = artistJson.getName() + "new";
		mainPageSteps.
				clickArtist().
				setNameInFindField(artistJson.getName()).
				clickFind().
				goToEditForm(artistJson.getName()).
				clickEditButton().
				editPhoto(artistJson.getPhoto()).
				editName(newName).
				clickSubmitButton().
				checkAlertEditArtist(newName).
				checkAvatar().
				checkArtistName(newName);

	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestArtist
	@DisplayName("Отменить изменение имени в профиле художника")
	public void cancelEditNameInProfile(ArtistJson artistJson) {
		String newName = artistJson.getName() + "new";
		mainPageSteps.
				clickArtist().
				setNameInFindField(artistJson.getName()).
				clickFind().
				goToEditForm(artistJson.getName()).
				clickEditButton().
				editPhoto(artistJson.getPhoto()).
				editName(newName).
				clickCloseButton().
				checkArtistName(artistJson.getName());

	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestArtist
	@DisplayName("Изменить биографию в профиле художника")
	public void editBiographyInProfile(ArtistJson artistJson) {
		String biography = artistJson.getBiography() + "new";
		mainPageSteps.
				clickArtist().
				setNameInFindField(artistJson.getName()).
				clickFind().
				goToEditForm(artistJson.getName()).
				clickEditButton().
				editPhoto(artistJson.getPhoto()).
				editBiography(biography).
				clickSubmitButton().
				checkAlertEditArtist(artistJson.getName()).
				checkAvatar().
				checkArtistBiography(biography);
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestArtist
	@DisplayName("Отменить изменение  биографии в профиле художника")
	public void cancelEditBiographyInProfile(ArtistJson artistJson) {
		String biography = artistJson.getBiography() + "new";
		mainPageSteps.
				clickArtist().
				setNameInFindField(artistJson.getName()).
				clickFind().
				goToEditForm(artistJson.getName()).
				clickEditButton().
				editPhoto(artistJson.getPhoto()).
				editBiography(biography).
				clickCloseButton().
				checkArtistBiography(artistJson.getBiography());
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestMuseum
	@TestArtist
	@DisplayName("Добавить картину в профиле художника")
	public void addPaintingInProfile(MuseumJson museumJson, ArtistJson artistJson) {
		PaintingJson paintingJson = new PaintingJson().random();
		mainPageSteps.
				clickArtist().
				setNameInFindField(artistJson.getName()).
				clickFind().
				goToEditForm(artistJson.getName()).
				clickAddPainting().
				setPaintingName(paintingJson.getTitle()).
				setPaintingImage(paintingJson.getContent()).
				setPaintingDescription(paintingJson.getDescription()).
				selectMuseum(museumJson.getTitle()).
				clickSubmitButton().
				checkAlert("Добавлена картина: "+paintingJson.getTitle());
	}
}
