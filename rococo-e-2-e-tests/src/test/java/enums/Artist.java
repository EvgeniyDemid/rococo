package enums;

public enum Artist {
	TITLE_ADD_FORM("Новый художник"),
	DESCRIPTION_ADD_FORM("Заполните форму, чтобы добавить нового художника"),
	NAME_ADD_FORM("Имя"),
	NAME_ADD_FORM_PLACEHOLDER("Введите имя художника..."),
	ARTIST_IMAGE_ADD_FORM("Изображение художника"),
	BIOGRAPHY_ADD_FORM("Биография"),
	BIOGRAPHY_ADD_FORM_PLACEHOLDER("Биография художника");

	private final String value;

	Artist(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
