package enums;


public enum Fields {
	USER_NAME("Имя пользователя", "Введите имя пользователя..."),
	PASSWORD("Пароль", "Введите пароль...");

	private final String name;
	private final String placeholder;

	Fields(final String name, final String placeholder) {
		this.name = name;
		this.placeholder = placeholder;
	}

	public String getName() {
		return this.name;
	}

	public String getPlaceholder() {
		return this.placeholder;
	}
}
