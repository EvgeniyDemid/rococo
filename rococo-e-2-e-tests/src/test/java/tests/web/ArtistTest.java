package tests.web;

import jdk.jfr.Description;
import jupiter.annotation.ApiLogin;
import jupiter.annotation.TestArtist;
import jupiter.annotation.TestPainting;
import jupiter.annotation.TestUser;
import jupiter.extension.ArtistExtension;
import jupiter.extension.PaintingExtension;
import model.ArtistJson;
import model.PaintingJson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import testData.ArtistData;

import static enums.Errors.BIOGRAPHY_MIN_SYMBOL;
import static enums.Errors.NAME_MIN_SYMBOL;
import static enums.UserType.RANDOM_REGISTERED_USER;

@ExtendWith({
		ArtistExtension.class,
		PaintingExtension.class})
public class ArtistTest extends BaseWebTest {


	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@Description("Проверка полей формы добавления художника")
	public void checkAddForm() {
		mainPageSteps.
				clickArtist().
				clickAddArtist().
				checkAddArtist();
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@Description("Валидация полей формы добавления художника")
	public void validationFieldAddForm() {
		mainPageSteps.
				clickArtist().
				clickAddArtist().
				setArtistName("1").
				setImage(ArtistData.artistData[0].getPhoto()).
				setBiography("1").
				clickAddButton().
				checkErrorNameOnNewArtist(NAME_MIN_SYMBOL.getValue())
				.checkErrorBiographyOnNewArtist(BIOGRAPHY_MIN_SYMBOL.getValue());

	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@Description("Добавление художника")
	public void addArtist() {
		ArtistJson artistJson = ArtistData.artistData[0];
		mainPageSteps.
				clickArtist().
				clickAddArtist().
				setArtistName(artistJson.getName()).
				setImage(ArtistData.artistData[0].getPhoto()).
				setBiography(artistJson.getBiography()).
				clickAddButton().
				checkAlertAddNewArtist(artistJson.getName());
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestArtist
	@Description("Поиск художника")
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
	@Description("Отмена добавления художника")
	public void cancelAddArtist() {
		ArtistJson artistJson = ArtistData.artistData[0];
		mainPageSteps.
				clickArtist().
				clickAddArtist().
				setArtistName(artistJson.getName()).
				setImage(ArtistData.artistData[0].getPhoto()).
				setBiography(artistJson.getBiography()).
				clickCloseButton().
				checkArtistInListNoExit(artistJson.getName());
	}

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestPainting
	@Description("Добавить картину в профиле художника")
	public void addPaintingInProfile(PaintingJson paintingJson) {
		System.out.println();
	}
}
