package jupiter.extension;

import data.entity.UserAuthEntity;
import data.entity.UserEntity;
import data.repository.UserRepositoryStringJdbc;
import jupiter.annotation.TestUser;
import model.UserJson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import static enums.UserType.*;

public class UserExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {

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
	public void beforeEach(ExtensionContext extensionContext) {
		AnnotationSupport.
				findAnnotation(
						extensionContext.getRequiredTestMethod(),
						TestUser.class).ifPresent(
						testUser -> {
							if (testUser.USER_TYPE().equals(RANDOM_NO_REGISTERED_USER)) {
								extensionContext.
										getStore(NAMESPACE).
										put(extensionContext.getUniqueId(),
												UserJson.randomUser());
							} else if (testUser.USER_TYPE().equals(RANDOM_REGISTERED_USER)) {
								extensionContext.getStore(NAMESPACE).put(extensionContext.getUniqueId(),
										createUser(UserJson.randomUser()));
							} else if (testUser.username() != null) {
								UserJson userJson = UserJson.userJsonByUserNameAndPassword(testUser.username(), testUser.password());
								if (testUser.USER_TYPE().equals(REGISTERED)) {
									extensionContext.getStore(NAMESPACE).put(extensionContext.getUniqueId(), createUser(userJson));
								} else {
									extensionContext.getStore(NAMESPACE).put(extensionContext.getUniqueId(), userJson);
								}
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

	@Override
	public void afterEach(ExtensionContext context) {
		AnnotationSupport.
				findAnnotation(
						context.getRequiredTestMethod(),
						TestUser.class
				).ifPresent(user -> {
					if (user.username() != null) {
						userRepositoryStringJdbc.
								deleteUserByUserName(
										context.getStore(NAMESPACE).
												get(context.getUniqueId(), UserJson.class).username()
								);
					}
				});
	}
}
