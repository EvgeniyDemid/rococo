package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import ui.steps.MainPageSteps;
import ui.steps.RegisterSteps;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.Objects;

public class BaseTest {
	MainPageSteps mainPageSteps= new MainPageSteps();

	static {
		Configuration.browserSize = "1920x1080";
	}

	@BeforeEach
	void set() {
		Allure.getLifecycle().updateTestCase(testResult -> {
			testResult.setStart(new Date().getTime());
		});
	}

//	@AfterEach
//	void doScreenshot() {
//		Allure.addAttachment(
//				"Screen on test end",
//				new ByteArrayInputStream(
//						Objects.requireNonNull(
//								Selenide.screenshot(OutputType.BYTES)
//						)
//				)
//		);
//	}
}
