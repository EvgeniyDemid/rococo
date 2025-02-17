package tests.apiRetrofit;

import api.artist.ArtistServiceClient;
import data.entity.ArtistEntity;
import data.repository.ArtistRepositorySpringJdbc;
import jupiter.annotation.ApiLogin;
import jupiter.annotation.TestArtist;
import jupiter.annotation.TestUser;
import jupiter.annotation.meta.ApiTest;
import jupiter.extension.ApiLoginExtension;
import jupiter.extension.ArtistExtension;
import model.ArtistJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Optional;

import static enums.UserType.RANDOM_REGISTERED_USER;
import static org.junit.jupiter.api.Assertions.*;

@ApiTest
@ExtendWith(ArtistExtension.class)
public class ArtistTest {
	ArtistServiceClient artistServiceClient = new ArtistServiceClient();
	private final ArtistRepositorySpringJdbc artistJdbc = new ArtistRepositorySpringJdbc();

	@Test
	@ApiLogin()
	@TestArtist
	@DisplayName("Проверка в списке художника")
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	public void getArtist(ArtistJson artistJson) throws IOException {
		Optional<ArtistJson> artistCreateApi = artistServiceClient.
				getAllArtist(ApiLoginExtension.getToken()).
				stream().filter(
						artistJson2 -> artistJson2.getId().equals(artistJson.getId())
				).findFirst();

		assertTrue(artistCreateApi.isPresent(), "Художник не найден");
		artistCreateApi.ifPresent(artist -> {
			assertEquals(artistJson.getName(), artist.getName(), "Имя художника не совпадает");
			assertEquals(artistJson.getBiography(), artist.getBiography(), "Биография художника не совпадает");
		});
	}

	@Test
	@ApiLogin()
	@DisplayName("Создание художника")
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	public void createArtist() throws IOException {
		ArtistJson artistCreate = artistServiceClient.createArtist(new ArtistJson().random(), ApiLoginExtension.getToken());
		ArtistEntity artistInDb = artistJdbc.findArtistById(artistCreate.getId());
		assertNotNull(artistInDb, "Художник не найден");
		assertEquals(artistCreate.getName(), artistInDb.getName(), "Имя художника не совпадает");
		assertEquals(artistCreate.getBiography(), artistInDb.getBiography(), "Биография художника не совпадает");
	}

	@Test
	@ApiLogin()
	@TestArtist()
	@DisplayName("Обновление художника")
	@TestUser(USER_TYPE = RANDOM_REGISTERED_USER)
	public void updateArtist(ArtistJson artistJson) throws IOException {
		ArtistJson newArtist = new ArtistJson().random();
		newArtist.setId(artistJson.getId());
		ArtistJson artistCreate =
				artistServiceClient.updateArtist(newArtist, ApiLoginExtension.getToken());
		ArtistEntity artistInDb = artistJdbc.findArtistById(artistCreate.getId());
		assertNotNull(artistInDb, "Художник не найден");
		assertEquals(artistCreate.getName(), artistInDb.getName(), "Имя художника не совпадает");
		assertEquals(artistCreate.getBiography(), artistInDb.getBiography(), "Биография художника не совпадает");
	}
}