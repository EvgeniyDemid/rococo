package tests.apiAssured;

import config.Config;
import io.restassured.RestAssured;
import jupiter.annotation.meta.ApiTest;
import org.junit.jupiter.api.BeforeAll;

@ApiTest
public class BaseTest {

	private static final Config CFG = Config.getInstance();

	@BeforeAll
	static void beforeAll() {
		RestAssured.baseURI = CFG.gatewayUrl();
		RestAssured.basePath = "/api";
	}
}
