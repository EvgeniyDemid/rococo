package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.UUID;

public record UserJson(
		@JsonProperty("id")
		UUID id,
		@JsonProperty("username")
		String username,
		@JsonProperty("firstname")
		@Size(max = 30, message = "Имя не может быть длиннее 30 символов")
		String firstname,
		@JsonProperty("lastname")
		@Size(max = 50, message = "Фамилия не может быть длиннее 30 символов")
		String lastname,
		@JsonProperty("avatar")
		String avatar) {

	public @Nonnull UserJson addUsername(@Nonnull String username) {
		return new UserJson(id, username, firstname, lastname, avatar);
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserJson userJson = (UserJson) o;
		return Objects.equals(id, userJson.id) && Objects.equals(username, userJson.username) && Objects.equals(firstname, userJson.firstname) && Objects.equals(lastname, userJson.lastname) && Objects.equals(avatar, userJson.avatar);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, firstname, lastname, avatar);
	}
}