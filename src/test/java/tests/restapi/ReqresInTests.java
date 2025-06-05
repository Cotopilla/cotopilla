package tests.restapi;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.is;

@Owner("Evgeniya Malysheva")
public class ReqresInTests extends TestBase {

    int validUserId = 2;
    int notValidUserId = 23;

    @Test
    @DisplayName("Запрос данных по несуществующему пользователю")
    void singleUserNotFound404Test() {
        given()
                .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
                .log().uri()

                .when()
                .get(USERS_END_POINT + notValidUserId)

                .then()
                .log().status()
                .statusCode(HTTP_NOT_FOUND)
                .body(is("{}"));
    }

    @Test
    @DisplayName("Изменение только должности пользователя (через patch)")
    void successfulPatchUserJobTest() {
        String newUserDataBody = "{\"job\": \"zion resident\"}";

        given()
                .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
                .body(newUserDataBody)
                .contentType(JSON)
                .log().uri()

                .when()
                .patch(USERS_END_POINT + validUserId)

                .then()
                .log().status()
                .log().body()
                .statusCode(HTTP_OK)
                .body("job", is("zion resident"));
    }

    @Test
    @DisplayName("Изменение должности пользователя через обновление всех полей записи (put)")
    void successfulUpdateUserJobTest() {
        String newUserDataBody = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";

        given()
                .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
                .body(newUserDataBody)
                .contentType(JSON)
                .log().uri()

                .when()
                .put(USERS_END_POINT + validUserId)

                .then()
                .log().status()
                .log().body()
                .statusCode(HTTP_OK)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void successfulCreateUserTest() {
        String userDataBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
                .body(userDataBody)
                .contentType(JSON)
                .log().uri()

                .when()
                .post(USERS_END_POINT)

                .then()
                .log().status()
                .log().body()
                .statusCode(HTTP_CREATED)
                .body("name", is("morpheus"));
    }

    @Test
    @DisplayName("Удаление пользователя")
    void successfulDeleteUserTest() {
        given()
                .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
                .contentType(JSON)
                .log().uri()

                .when()
                .delete(USERS_END_POINT + validUserId)

                .then()
                .log().status()
                .statusCode(HTTP_NO_CONTENT);
    }

    @Test
    @DisplayName("Неуспешная регистрация: отсутствует пароль")
    void errorMissingPasswordRegisterTest() {
        String userCredentials = "{\"email\": \"sydney@fife\"}";

        given()
                .header(FREE_API_KEY_NAME, FREE_API_KEY_VALUE)
                .body(userCredentials)
                .contentType(JSON)
                .log().uri()

                .when()
                .post(REGISTER_END_POINT)

                .then()
                .log().status()
                .log().body()
                .statusCode(HTTP_BAD_REQUEST)
                .body("error", is("Missing password"));
    }

}
