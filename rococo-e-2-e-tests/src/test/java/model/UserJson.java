package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserJson(
		@JsonProperty("id")
		UUID id,
		@JsonProperty("username")
		String username,
		@JsonProperty("firstname")
		String firstname,
		@JsonProperty("lastname")
		String lastname,
		@JsonProperty("avatar")
		String avatar,
		@JsonIgnore
		String password) {

	private static final Faker faker = new Faker(new Locale("en"));

	public static UserJson randomUser() {
		return new UserJson(
				null,
				faker.name().username(),
				faker.name().firstName(),
				faker.name().lastName(),
				null,
				faker.internet().password());
	}
}