package guru.qa.service;

import guru.qa.model.UserJson;
import jakarta.annotation.Nonnull;

public interface UserDataClient {
	@Nonnull
	UserJson updateUserInfo(@Nonnull UserJson user);

	@Nonnull
	UserJson currentUser(@Nonnull String username);
}
