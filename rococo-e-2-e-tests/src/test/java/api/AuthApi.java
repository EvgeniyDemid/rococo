package api;

import model.oauth.TokenJson;
import retrofit2.Call;
import retrofit2.http.*;

public interface AuthApi {

	@GET("oauth2/authorize")
	Call<Void> preRequest(
			@Query("response_type") String responseType,
			@Query("client_id") String clientId,
			@Query("scope") String scope,
			@Query(value = "redirect_uri", encoded = true) String redirectUri,
			@Query("code_challenge") String codeChallenge,
			@Query("code_challenge_method") String codeChallengeMethod
	);

	@POST("login")
	@FormUrlEncoded
	Call<Void> login(
			@Field("_csrf") String csrf,
			@Field("username") String username,
			@Field("password") String password
	);

	@POST("oauth2/token")
	Call<TokenJson> token(
			@Header("Authorization") String basicAuthorizationHeader,
			@Query("client_id") String clientId,
			@Query(value = "redirect_uri", encoded = true) String redirectUri,
			@Query("grant_type") String grantType,
			@Query("code") String code,
			@Query("code_verifier") String codeVerifier
	);
}
