package ui.steps;

import config.Config;
import io.qameta.allure.Step;
import ui.form.CommonElements;
import ui.page.LoginPage;

import static com.codeborne.selenide.Condition.text;

public class CommonSteps <T extends CommonSteps<?>> {

	protected static final Config CFG = Config.getInstance();

	LoginPage loginPage = new LoginPage();

	CommonElements commonElements = new CommonElements();

	@Step("Проверить текст ошибки  '{error}'")
	@SuppressWarnings("unchecked")
	public T checkErrorText(String error) {
		loginPage.onLoginForm().error.shouldHave(text(error));
		return  (T) this;
	}

	@Step("Вести в поле поиска { value }")
	@SuppressWarnings("unchecked")
	public T setNameInFindField(String value){
		commonElements.searchInput.setValue(value);
		return  (T) this;
	}
	@Step("Нажать на кнопку 'Найти' ")
	@SuppressWarnings("unchecked")
	public T clickFind(){
		commonElements.searchButton.click();
		return  (T) this;
	}
	@Step("Нажать на кнопку 'Закрыть' ")
	@SuppressWarnings("unchecked")
	public T clickCloseButton(){
		commonElements.closeButton.click();
		return  (T) this;
	}
	@Step("Нажать на кнопку 'Добавить' ")
	@SuppressWarnings("unchecked")
	public T clickSubmitButton(){
		commonElements.submitButton.click();
		return  (T) this;
	}
	@Step("Проверить текст алерта ")
	@SuppressWarnings("unchecked")
	public T checkAlert(String text){
	commonElements.	alert.shouldBe(text(text));
	return (T) this;
	}
}
