package tests.restapi.lesson15_16;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import models.lombok.lesson16.LoginBodyLombokModel;
import models.lombok.lesson16.LoginResponseLombokModel;
import models.lombok.lesson16.MissingPasswordModel;
import models.pojo.LoginBodyModel;
import models.pojo.LoginResponseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static helpers.allure.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.lesson16.LoginSpec.*;

    public class LoginExtendedTests {
 /*
    1. Make request (POST) to https://reqres.in/api/login
        with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
    2. Get response { "token": "QpwL5tke4Pnpja7X4" }
    3. Check "token" is "QpwL5tke4Pnpja7X4" and status code 200
  */

        @BeforeAll
        public static void setUp() {
            RestAssured.baseURI = "https://reqres.in";
        }

        @Test
        void successfulLoginBadPracticeTest() {
            String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

            given()
                    .spec(authRequestSpec)
                    .body(authData)
                    .contentType(JSON)
                    .log().uri()
                    .log().body()
                    .log().headers()

                    .when()
                    .post("https://reqres.in/api/login")

                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .body("token", is("QpwL5tke4Pnpja7X4"));
        }

        @Test
        void successfulLoginPojoTest() {
            LoginBodyModel authData = new LoginBodyModel();
            authData.setEmail("eve.holt@reqres.in");
            authData.setPassword("cityslicka");

            LoginResponseModel response = given()
                    .spec(authRequestSpec)
                    .body(authData)
                    .contentType(JSON)
                    .log().uri()
                    .log().body()
                    .log().headers()

                    .when()
                    .post("https://reqres.in/api/login")

                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .extract().as(LoginResponseModel.class);

            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        }

        @Test
        void successfulLoginLombokTest() {
            LoginBodyLombokModel authData = new LoginBodyLombokModel();
            authData.setEmail("eve.holt@reqres.in");
            authData.setPassword("cityslicka");

            LoginResponseLombokModel response = given()
                    .spec(authRequestSpec)
                    .body(authData)
                    .contentType(JSON)
                    .log().uri()
                    .log().body()
                    .log().headers()

                    .when()
                    .post("https://reqres.in/api/login")

                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .extract().as(LoginResponseLombokModel.class);

            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        }

        @Test
        void successfulLoginAllureTest() {
            LoginBodyLombokModel authData = new LoginBodyLombokModel();
            authData.setEmail("eve.holt@reqres.in");
            authData.setPassword("cityslicka");

            LoginResponseLombokModel response = given()
                    .spec(authRequestSpec)
                    .filter(new AllureRestAssured())
                    .log().uri()
                    .log().body()
                    .log().headers()
                    .body(authData)
                    .contentType(JSON)

                    .when()
                    .post("https://reqres.in/api/login")

                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .extract().as(LoginResponseLombokModel.class);

            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        }

        @Test
        void successfulLoginCustomAllureTest() {
            LoginBodyLombokModel authData = new LoginBodyLombokModel();
            authData.setEmail("eve.holt@reqres.in");
            authData.setPassword("cityslicka");

            LoginResponseLombokModel response = given()
                    .spec(authRequestSpec)
                    .filter(withCustomTemplates())
                    .log().uri()
                    .log().body()
                    .log().headers()
                    .body(authData)
                    .contentType(JSON)

                    .when()
                    .post("https://reqres.in/api/login")

                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .extract().as(LoginResponseLombokModel.class);

            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        }

        @Test
        void successfulLoginWithStepsTest() {
            LoginBodyLombokModel authData = new LoginBodyLombokModel();
            authData.setEmail("eve.holt@reqres.in");
            authData.setPassword("cityslicka");

            LoginResponseLombokModel response = step("Make request", ()->
                    given()
                            .spec(authRequestSpec)
                            .filter(withCustomTemplates())
                            .log().uri()
                            .log().body()
                            .log().headers()
                            .body(authData)
                            .contentType(JSON)

                            .when()
                            .post("https://reqres.in/api/login")

                            .then()
                            .log().status()
                            .log().body()
                            .statusCode(200)
                            .extract().as(LoginResponseLombokModel.class));

            step("Check response", ()->
                    assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
        }

        @Test
        void successfulLoginWithSpecsTest() {
            LoginBodyLombokModel authData = new LoginBodyLombokModel();
            authData.setEmail("eve.holt@reqres.in");
            authData.setPassword("cityslicka");

            LoginResponseLombokModel response = step("Make request", ()->
                    given(loginRequestSpec)
                            .body(authData)

                            .when()
                            .post()

                            .then()
                            .spec(loginResponseSpec)
                            .extract().as(LoginResponseLombokModel.class));

            step("Check response", ()->
                    assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
        }


        @Test
        void missingPasswordTest() {
            LoginBodyLombokModel authData = new LoginBodyLombokModel();
            authData.setEmail("eve.holt@reqres.in");

            MissingPasswordModel response = step("Make request", ()->
                    given(loginRequestSpec)
                            .body(authData)

                            .when()
                            .post()

                            .then()
                            .spec(missingPasswordResponseSpec)
                            .extract().as(MissingPasswordModel.class));

            step("Check response", ()->
                    assertEquals("Missing password", response.getError()));
        }

    }
