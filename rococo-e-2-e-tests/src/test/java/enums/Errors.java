package enums;


public enum Errors {
	INVALID_CREDENTIALS("Неверные учетные данные пользователя");

	private final String value;

	Errors(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
