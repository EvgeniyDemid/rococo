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

import static enums.UserType.RANDOM_REGISTERED_USER;

@ExtendWith({
		ArtistExtension.class,
		PaintingExtension.class,
		MuseumExtension.class})
@DisplayName("Картины")
public class PaintingTest extends BaseWebTest {

	@Test
	@ApiLogin()
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestPainting
	@DisplayName("Проверить поиск картины")
	public void checkSearch(PaintingJson painting) {
		mainPageSteps.
				clickPainting().
				setPaintingInSearchField(painting.getTitle()).
				clickSearchButton().
				checkPaintingIsVisible(painting.getTitle());
	}

	@Test
	@ApiLogin
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestMuseum
	@TestArtist
	@DisplayName("Добавление картины")
	public void addPainting(MuseumJson museum, ArtistJson artist) {
		PaintingJson paint = new PaintingJson().random();
		mainPageSteps.
				clickPainting().
				clickAddPainting().
				setPaintingName(paint.getTitle()).
				setPaintingImage(paint.getContent()).
				selectArtist(artist.getName()).
				setDescription(paint.getDescription()).
				selectMuseum(museum.getTitle()).
				clickSubmitButton().
				goToPaintingPage(paint.getTitle()).
				checkPaintingName(paint.getTitle()).
				checkPaintingArtist(artist.getName()).
				checkPaintingDescription(paint.getDescription());
	}


	@Test
	@ApiLogin
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestPainting
	@DisplayName("Изменить название картины")
	public void editPaintingName(PaintingJson painting) {
		String paintName = painting.getTitle() + "New";
		String paint = new PaintingJson().random().getContent();
		mainPageSteps.
				clickPainting().
				goToPaintingPage(painting.getTitle()).
				clickEdit().
				setPaintingImage(paint).
				setPaintingName(paintName).
				selectArtist(painting.getArtist().getName()).
				selectMuseum(painting.getMuseum().getTitle()).
				clickSubmitButton().
				checkAlert("Обновлена картина: " + paintName).
				checkPaintingName(paintName);
	}

	@Test
	@ApiLogin
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestPainting
	@TestArtist
	@DisplayName("Изменить художника картины")
	public void editPaintingArtist(ArtistJson artistJson, PaintingJson paintingJson) {
		String paint = new PaintingJson().random().getContent();
		mainPageSteps.
				clickPainting().
				goToPaintingPage(paintingJson.getTitle()).
				clickEdit().
				setPaintingImage(paint).
				selectArtist(artistJson.getName()).
				selectMuseum(paintingJson.getMuseum().getTitle()).
				clickSubmitButton().
				checkAlert("Обновлена картина: " + paintingJson.getTitle()).
				checkPaintingArtist(artistJson.getName());
	}

	@Test
	@ApiLogin
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestPainting
	@DisplayName("Изменить описание картины")
	public void editPaintingDescription(PaintingJson paintingJson) {
		String paint = new PaintingJson().random().getContent();
		String description = paintingJson.getDescription() + "New";
		mainPageSteps.
				clickPainting().
				goToPaintingPage(paintingJson.getTitle()).
				clickEdit().
				setPaintingImage(paint).
				selectArtist(paintingJson.getArtist().getName()).
				setDescription(description).
				selectMuseum(paintingJson.getMuseum().getTitle()).
				clickSubmitButton().
				checkAlert("Обновлена картина: " + paintingJson.getTitle()).
				checkPaintingDescription(description);
	}

	@Test
	@ApiLogin
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@TestPainting
	@TestMuseum
	@DisplayName("Изменить музей картины")
	public void editPaintingMuseum(PaintingJson paintingJson,MuseumJson museumJson) {
		String paint = new PaintingJson().random().getContent();
		mainPageSteps.
				clickPainting().
				goToPaintingPage(paintingJson.getTitle()).
				clickEdit().
				setPaintingImage(paint).
				selectArtist(paintingJson.getArtist().getName()).
				selectMuseum(museumJson.getTitle()).
				clickSubmitButton().
				checkAlert("Обновлена картина: " + paintingJson.getTitle());
	}
}
