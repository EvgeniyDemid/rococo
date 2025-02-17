package tests.apiRetrofit;

import api.museum.MuseumServiceClient;
import data.repository.MuseumRepositorySpringJdbc;
import jupiter.annotation.ApiLogin;
import jupiter.annotation.TestCountry;
import jupiter.annotation.TestMuseum;
import jupiter.annotation.TestUser;
import jupiter.annotation.meta.ApiTest;
import jupiter.extension.ApiLoginExtension;
import jupiter.extension.CountryExtension;
import jupiter.extension.MuseumExtension;
import model.CountryJson;
import model.MuseumJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static enums.UserType.RANDOM_REGISTERED_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ApiTest
@ExtendWith({MuseumExtension.class, CountryExtension.class})
public class MuseumTest {
	private final MuseumServiceClient museumClient = new MuseumServiceClient();
	private final MuseumRepositorySpringJdbc museumJdbc = new MuseumRepositorySpringJdbc();

	@Test
	@ApiLogin()
	@TestMuseum
	@DisplayName("Получить музей")
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	public void getMuseum(MuseumJson museumJson) throws IOException {
		equalsMuseum(museumJson, museumClient.getMuseum(ApiLoginExtension.getToken(), museumJson.getId()));
	}

	@Test
	@ApiLogin()
	@TestCountry
	@DisplayName("Создать музей")
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	public void addMuseum(CountryJson countryJson) throws IOException {
		MuseumJson museumJson = new MuseumJson().random();
		museumJson.getGeo().setCountry(countryJson);
		museumJson = museumClient.addMuseum(ApiLoginExtension.getToken(), museumJson);
		equalsMuseum(museumJson, new MuseumJson().fromEntity(museumJdbc.findByIdMuseum(museumJson.getId())));
	}

	@Test
	@ApiLogin()
	@TestMuseum
	@DisplayName("Изменить музей")
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	public void updateMuseum(MuseumJson museumJson) throws IOException {
		MuseumJson newMuseum = new MuseumJson().random();
		newMuseum.setId(museumJson.getId());
		museumClient.updateMuseum(ApiLoginExtension.getToken(), museumJson);
		equalsMuseum(
				museumClient.updateMuseum(ApiLoginExtension.getToken(), museumJson),
				new MuseumJson().fromEntity(museumJdbc.findByIdMuseum(museumJson.getId())
				));
	}

	private void equalsMuseum(MuseumJson museumExpect, MuseumJson museumActual) {
		assertEquals(
				museumExpect.getTitle(),
				museumActual.getTitle(),
				"Название музеев не совпадают"
		);
		assertEquals(
				museumExpect.getDescription(),
				museumActual.getDescription(),
				"Описание музеев не совпадают"
		);
		assertEquals(
				museumExpect.getGeo().getCity(),
				museumActual.getGeo().getCity(),
				"Города музеев не совпадают"
		);
		assertEquals(
				museumExpect.getGeo().getCountry().getId(),
				museumActual.getGeo().getCountry().getId(),
				"Страны музеев не совпадают"
		);
	}
}
