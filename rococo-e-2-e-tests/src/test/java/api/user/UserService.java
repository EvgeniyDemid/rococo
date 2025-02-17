package api.user;

import model.UserJson;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;

public interface UserService {

	@GET("api/user")
	Call<UserJson> user(
			@Header("Authorization") String basicAuthorizationHeader
	);

	@PATCH("api/user")
	Call<UserJson> userProfileUpdate(
			@Header("Authorization") String basicAuthorizationHeader,
			@Body UserJson userJson
	);

}
