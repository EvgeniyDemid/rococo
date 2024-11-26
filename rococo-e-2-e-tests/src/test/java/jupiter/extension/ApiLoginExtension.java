package jupiter.extension;

import api.AuthApiClient;
import api.cookie.ThreadSafeCookieStore;
import jupiter.annotation.ApiLogin;
import model.UserJson;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

import static jupiter.extension.ContextExtension.context;

public class ApiLoginExtension implements BeforeEachCallback, AfterEachCallback {

	public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(ApiLoginExtension.class);
	private final AuthApiClient authApiClient = new AuthApiClient();


	@Override
	public void beforeEach(ExtensionContext context)  {
		UserJson user = context.getStore(UserExtension.NAMESPACE)
				.get(context.getUniqueId(), UserJson.class);

		AnnotationSupport.findAnnotation(
				context.getRequiredTestMethod(),
				ApiLogin.class
		).ifPresent(annotation -> {
			if (user!= null) {
				authApiClient.doLogin(user.username(), user.password());
				context.getStore(NAMESPACE).put(context.getUniqueId(), user);
			}
		});
	}

	@Override
	public void afterEach(ExtensionContext context) {
		ThreadSafeCookieStore.INSTANCE.clearCookies();
	}


	public static void setToken(String token) {
		context().getStore(NAMESPACE).put("token", token);
	}

	public static String getToken() {
		return context().getStore(NAMESPACE).get("token", String.class);
	}

	public static void setCodeChallenge(String codeChallenge) {
		context().getStore(NAMESPACE).put("cc", codeChallenge);
	}

	public static String getCodeChallenge() {
		return context().getStore(NAMESPACE).get("cc", String.class);
	}

	public static void setCodeVerifier(String codeVerifier) {
		context().getStore(NAMESPACE).put("cv", codeVerifier);
	}

	public static String getCodeVerifier() {
		return context().getStore(NAMESPACE).get("cv", String.class);
	}

	public static void setCode(String code) {
		context().getStore(NAMESPACE).put("c", code);
	}

	public static String getCode() {
		return context().getStore(NAMESPACE).get("c", String.class);
	}

}
