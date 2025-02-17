package tests.apiAssured;

import data.entity.PaintingEntity;
import data.repository.PaintingRepositorySpringJdbc;
import jupiter.annotation.*;
import jupiter.extension.ApiLoginExtension;
import jupiter.extension.ArtistExtension;
import jupiter.extension.MuseumExtension;
import model.ArtistJson;
import model.MuseumJson;
import model.PaintingJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static api.specs.PaintingSpec.paintingRequestSpec;
import static api.specs.PaintingSpec.paintingResponseSpec;
import static enums.UserType.RANDOM_REGISTERED_USER;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({ArtistExtension.class, MuseumExtension.class})
public class PaintingTest extends BaseTest {

	private final PaintingRepositorySpringJdbc paintingJdbc = new PaintingRepositorySpringJdbc();

	@Test
	@ApiLogin()
	@TestPainting
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Получение картины")
	public void getPainting(PaintingJson paint) {
		PaintingJson paintingJson = step("Сделать запрос на получение картины", () -> given(paintingRequestSpec).
				auth().
				oauth2(ApiLoginExtension.getToken()).
				pathParam("id", paint.getId()).
				when().
				get("/painting/{id}").
				then().
				spec(paintingResponseSpec).
				extract().
				as(PaintingJson.class));

		checkPainting(paint, paintingJson);
	}

	@Test
	@ApiLogin()
	@TestArtist
	@TestMuseum
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Добавление картины")
	public void addPainting(ArtistJson artistJson, MuseumJson museumJson) {
		PaintingJson paint = new PaintingJson().random();
		paint.setMuseum(museumJson);
		paint.setArtist(artistJson);
		PaintingJson paintingJson = step("Сделать запрос на создание  картины", () -> given(paintingRequestSpec).
				auth().
				oauth2(ApiLoginExtension.getToken()).
				body(paint).
				when().
				post("/painting").
				then().
				spec(paintingResponseSpec).
				extract().
				as(PaintingJson.class));

		PaintingEntity paintingEntity = paintingJdbc.findByIdPainting(paintingJson.getId());
		assertEquals(paintingJson.getTitle(), paintingEntity.getTitle(), "Не совпадает название картины");
		assertEquals(paintingJson.getDescription(), paintingEntity.getDescription(), "Не совпадает описание картины");
		assertEquals(paintingJson.getArtist().getId(), paintingEntity.getArtistId(), "Не совпадает художник картины");
		assertEquals(paintingJson.getMuseum().getId(), paintingEntity.getMuseumId(), "Не совпадает музей картины");
	}

	@Test
	@ApiLogin()
	@TestPainting
	@TestArtist
	@TestMuseum
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	@DisplayName("Обновление картины")
	public void editPainting(PaintingJson paint, ArtistJson artistJson, MuseumJson museumJson) {
		PaintingJson newPaint = new PaintingJson().random();
		newPaint.setId(paint.getId());
		newPaint.setArtist(artistJson);
		newPaint.setMuseum(museumJson);
		step("Сделать запрос на изменение картины", () -> given(paintingRequestSpec).
				auth().
				oauth2(ApiLoginExtension.getToken()).
				body(newPaint).
				when().
				patch("/painting").
				then().
				spec(paintingResponseSpec).
				extract());

		PaintingJson paintingJson = step("Сделать запрос на получение картины", () -> given(paintingRequestSpec).
				auth().
				oauth2(ApiLoginExtension.getToken()).
				pathParam("id", paint.getId()).
				when().
				get("/painting/{id}").
				then().
				spec(paintingResponseSpec).
				extract().
				as(PaintingJson.class));

		checkPainting(newPaint, paintingJson);
	}

	private void checkPainting(PaintingJson expected, PaintingJson actual) {
		assertEquals(expected.getTitle(), actual.getTitle(), "Не совпадает название картины");
		assertEquals(expected.getDescription(), actual.getDescription(), "Не совпадает описание картины");
		assertEquals(expected.getArtist().getName(), actual.getArtist().getName(), "Не совпадает художник картины");
		assertEquals(expected.getMuseum().getTitle(), actual.getMuseum().getTitle(), "Не совпадает музей картины");
	}
}
