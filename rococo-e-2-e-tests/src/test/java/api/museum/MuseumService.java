package api.museum;

import model.MuseumJson;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.UUID;

public interface MuseumService {

	@GET("/api/museum/{id}")
	Call<MuseumJson> findMuseumById(@Header("Authorization") String basicAuthorizationHeader, @Path("id") UUID uuid);

	@PATCH("/api/museum")
	Call<MuseumJson> updateMuseum(
			@Header("Authorization") String basicAuthorizationHeader,
			@Body MuseumJson museumJson
			);

	@POST("/api/museum")
	Call<MuseumJson> addMuseum( @Header("Authorization")String basicAuthorizationHeader, @Body MuseumJson museumJson);
}
