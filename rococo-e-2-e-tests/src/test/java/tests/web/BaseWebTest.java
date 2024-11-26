package tests.web;

import com.codeborne.selenide.Configuration;
import config.Config;
import io.qameta.allure.Allure;
import jupiter.annotation.meta.WebTest;
import org.junit.jupiter.api.BeforeEach;
import testData.ArtistData;
import ui.steps.ArtistSteps;
import ui.steps.LoginSteps;
import ui.steps.MainPageSteps;
import ui.steps.ProfileSteps;

import java.util.Date;

@WebTest
public class BaseWebTest {
	MainPageSteps mainPageSteps = new MainPageSteps();
	ProfileSteps profileSteps = new ProfileSteps();
	ArtistSteps artistSteps = new ArtistSteps();

	protected static final Config CFG = Config.getInstance();

	static {
		Configuration.browserSize = "1920x1080";
	}

	@BeforeEach
	void set() {
		Allure.getLifecycle().updateTestCase(testResult -> {
			testResult.setStart(new Date().getTime());
		});
	}
}
