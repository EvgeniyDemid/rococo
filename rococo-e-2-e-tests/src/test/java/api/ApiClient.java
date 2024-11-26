package api;

import api.cookie.ThreadSafeCookieStore;
import config.Config;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.CookieManager;

import static api.helpers.CustomAllureListenerRetrofit.withCustomTemplatesRetrofit;
import static java.net.CookiePolicy.ACCEPT_ALL;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public abstract class ApiClient {

	protected final OkHttpClient okHttpClient;
	protected final Retrofit retrofit;
	protected static final Config CFG = Config.getInstance();

	public ApiClient(String baseUrl) {
		this(baseUrl, false, JacksonConverterFactory.create(), BODY);
	}

	public ApiClient(String baseUrl, boolean followRedirect, Converter.Factory converter, HttpLoggingInterceptor.Level loggingLevel) {
		this(baseUrl, followRedirect, converter, loggingLevel, null);
	}

	public ApiClient(String baseUrl,
					 boolean followRedirect,
					 Converter.Factory converter,
					 HttpLoggingInterceptor.Level loggingLevel,
					 Interceptor... interceptors) {
		OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
		if (interceptors != null) {
			for (Interceptor interceptor : interceptors) {
				okHttpClientBuilder.addNetworkInterceptor(interceptor);
			}
		}
		okHttpClientBuilder.cookieJar(
				new JavaNetCookieJar(
						new CookieManager(ThreadSafeCookieStore.INSTANCE, ACCEPT_ALL)
				)
		);
		this.okHttpClient = okHttpClientBuilder.
				addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(loggingLevel)).
				followRedirects(followRedirect).
				build();

		this.retrofit = new Retrofit.Builder().
				baseUrl(baseUrl).
				addConverterFactory(converter).
				client(this.okHttpClient).
				build();
	}

	public ApiClient(
			String baseUrl,
			Converter.Factory converter,
			HttpLoggingInterceptor.Level loggingLevel
	) {

		OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

		this.okHttpClient = okHttpClientBuilder.
				addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(loggingLevel)).
				addInterceptor(withCustomTemplatesRetrofit()).
				build();

		this.retrofit = new Retrofit.Builder().
				baseUrl(baseUrl).
				addConverterFactory(converter).
				client(this.okHttpClient).
				build();
	}
}
