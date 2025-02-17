package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.helpers.CustomAllureListenerRestAssured.withCustomTemplatesRestAssured;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class PaintingSpec {
	public static RequestSpecification paintingRequestSpec = with()
			.filter(withCustomTemplatesRestAssured())
			.log().uri()
			.log().body()
			.log().headers()
			.contentType(JSON);
	public static ResponseSpecification paintingResponseSpec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.log(STATUS)
			.log(BODY)
			.build();
}
