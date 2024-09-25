package jupiter.extension;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;
import java.util.Objects;

public class BrowserExtension implements TestExecutionExceptionHandler,
		AfterEachCallback,
		LifecycleMethodExecutionExceptionHandler{

	@Override
	public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
		doScreenShot();
		throw throwable;
	}

	@Override
	public void handleBeforeEachMethodExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
		doScreenShot();
		throw throwable;
	}

	@Override
	public void handleAfterEachMethodExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
		doScreenShot();
		throw throwable;
	}

	private void doScreenShot() {
		if (WebDriverRunner.hasWebDriverStarted()) {
			Allure.addAttachment(
					"Screen on test end",
					new ByteArrayInputStream(
							Objects.requireNonNull(
									Selenide.screenshot(OutputType.BYTES)
							)
					)
			);
		}
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		if (WebDriverRunner.hasWebDriverStarted()) {
			Selenide.closeWebDriver();
		}
	}
}
