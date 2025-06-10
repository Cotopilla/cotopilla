package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.*;
import static tests.restapi.TestBase.FREE_API_KEY_NAME;
import static tests.restapi.TestBase.FREE_API_KEY_VALUE;

public class ReqresInSpecs {

    public static RequestSpecification requestSpec = with()
            .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON);

    public static ResponseSpecification updateResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(HTTP_OK)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification createResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(HTTP_CREATED)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification deleteResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(HTTP_NO_CONTENT)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification error404ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(HTTP_NOT_FOUND)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification error400ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(HTTP_BAD_REQUEST)
            .log(STATUS)
            .log(BODY)
            .build();
}
