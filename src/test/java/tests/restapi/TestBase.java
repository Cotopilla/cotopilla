package tests.restapi;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    public static final String FREE_API_KEY_NAME = "x-api-key";
    public static final String FREE_API_KEY_VALUE = "reqres-free-v1";
    public static final String USERS_END_POINT = "/users/";
    public static final String REGISTER_END_POINT = "/register";

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }


}
