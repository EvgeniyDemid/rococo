package enums;


public enum Errors {
	INVALID_CREDENTIALS("Неверные учетные данные пользователя"),
	USERNAME_EXISTS("Username `%s` already exists"),
	PASSWORD_LENGTH("Allowed password length should be from 3 to 12 characters"),
	PASSWORDS_SHOULD_BE_EQUAL("Passwords should be equal"),
	NAME_MIN_SYMBOL("Имя не может быть короче 3 символов"),
	BIOGRAPHY_MIN_SYMBOL("Биография не может быть короче 10 символов");

	private final String value;

	Errors(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public String getValue(String user) {
		return String.format(this.value,user);
	}
}
