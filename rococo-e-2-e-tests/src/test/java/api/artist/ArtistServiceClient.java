package api.artist;

import api.ApiClient;
import io.qameta.allure.Step;
import model.ArtistJson;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class ArtistServiceClient extends ApiClient {

	private final ArtistService artistService;

	public ArtistServiceClient() {
		super(
				CFG.gatewayUrl(),
				JacksonConverterFactory.create(),
				HttpLoggingInterceptor.Level.BODY
		);
		this.artistService = retrofit.create(ArtistService.class);
	}

	@Step("Получение списка все художников")
	public List<ArtistJson> getAllArtist(String bearerToken) throws IOException {
		String token = "Bearer " + bearerToken;
		return requireNonNull(artistService.getAll(token).execute().body()).getContent();
	}

	@Step("Создание художника")
	public ArtistJson createArtist(ArtistJson artistJson, String bearerToken) throws IOException {
		String token = "Bearer " + bearerToken;
		return requireNonNull(artistService.addArtist(artistJson, token).execute().body());
	}
	@Step("Изменить художника")
	public ArtistJson updateArtist(ArtistJson artistJson, String bearerToken) throws IOException {
		String token = "Bearer " + bearerToken;
		return requireNonNull(artistService.updateArtist(artistJson, token).execute().body());
	}
}


