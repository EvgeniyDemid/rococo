package jupiter.extension;

import data.entity.UserAuthEntity;
import data.entity.UserEntity;
import data.repository.UserRepositoryStringJdbc;
import jupiter.annotation.TestUser;
import model.UserJson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import static enums.UserType.RANDOM_USER;
import static enums.UserType.REGISTERED;

public class UserExtension implements BeforeEachCallback, ParameterResolver {

	private final UserRepositoryStringJdbc userRepositoryStringJdbc = new UserRepositoryStringJdbc();

	UserJson createUser(UserJson user) {
		UserEntity userEntity = userRepositoryStringJdbc.createUserInUserdata(new UserEntity().fromJson(user));
		UserAuthEntity userAuthEntity = userRepositoryStringJdbc.createUserInAuth(new UserAuthEntity().fromJson(user));

		return new UserJson(
				userEntity.getId(),
				userEntity.getUsername(),
				userEntity.getFirstname(),
				userEntity.getLastname(),
				null,
				userAuthEntity.getPassword()
		);
	}

	public static final ExtensionContext.Namespace NAMESPACE
			= ExtensionContext.Namespace.create(UserExtension.class);

	@Override
	public void beforeEach(ExtensionContext extensionContext) throws Exception {
		AnnotationSupport.
				findAnnotation(
						extensionContext.getRequiredTestMethod(),
						TestUser.class).ifPresent(
						testUser -> {
							if (testUser.USER_TYPE().equals(RANDOM_USER)) {
								extensionContext.
										getStore(NAMESPACE).
										put(extensionContext.getUniqueId(),
												UserJson.randomUser());
							} else if (testUser.USER_TYPE().equals(REGISTERED)) {
								extensionContext.getStore(NAMESPACE).put(extensionContext.getUniqueId(),
										createUser(UserJson.randomUser()));
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
