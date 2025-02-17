package api.artist;

import model.ArtistJson;
import retrofit2.Call;
import retrofit2.http.*;
import utils.RestResponsePage;

public interface ArtistService {

	@GET("/api/artist")
	Call<RestResponsePage<ArtistJson>> getAll(@Header("Authorization") String basicAuthorizationHeader);

	@PATCH("/api/artist")
	Call<ArtistJson> updateArtist(
			@Body ArtistJson artistJson,
			@Header("Authorization") String basicAuthorizationHeader);

	@POST("/api/artist")
	Call<ArtistJson> addArtist(@Body ArtistJson artistJson, @Header("Authorization") String basicAuthorizationHeader);
}
