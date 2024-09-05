package jupiter.extension;

import com.github.javafaker.Faker;
import jupiter.annotation.TestUser;
import model.UserJson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import static enums.UserType.RANDOM_USER;

public class UserExtension implements BeforeEachCallback, ParameterResolver {

	public static final ExtensionContext.Namespace NAMESPACE
			= ExtensionContext.Namespace.create(UserExtension.class);

	@Override
	public void beforeEach(ExtensionContext extensionContext) throws Exception {
				AnnotationSupport.
						findAnnotation(
								extensionContext.getRequiredTestMethod(),
								TestUser.class).ifPresent(
								testUser -> {
									if (testUser.USER_TYPE().equals(RANDOM_USER)){
										extensionContext.
												getStore(NAMESPACE).
												put(extensionContext.getUniqueId(),
														UserJson.randomUser());
									}
								}
						);

	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return parameterContext.getParameter().getType().isAssignableFrom(UserJson.class);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId());
	}
}
