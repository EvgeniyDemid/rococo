package api.museum;

import api.ApiClient;
import io.qameta.allure.Step;
import model.MuseumJson;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class MuseumServiceClient extends ApiClient {
	private final MuseumService museumService;

	public MuseumServiceClient() {
		super(
				CFG.gatewayUrl(),
				JacksonConverterFactory.create(),
				HttpLoggingInterceptor.Level.BODY
		);
		this.museumService = retrofit.create(MuseumService.class);
	}
	@Step("Получение музея")
	public MuseumJson getMuseum(String bearerToken, UUID uuid) throws IOException {
		String token = "Bearer " + bearerToken;
	return 	requireNonNull(museumService.findMuseumById(token,uuid)).execute().body();
	}

	@Step("Создание  музея")
	public MuseumJson addMuseum(String bearerToken, MuseumJson museumJson) throws IOException {
		String token = "Bearer " + bearerToken;
		return 	requireNonNull(museumService.addMuseum(token,museumJson)).execute().body();
	}

	@Step("Обновить  музей")
	public MuseumJson updateMuseum(String bearerToken, MuseumJson museumJson) throws IOException {
		String token = "Bearer " + bearerToken;
		return 	requireNonNull(museumService.updateMuseum(token,museumJson)).execute().body();
	}
}
