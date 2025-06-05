package tests.restapi;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@Owner("Evgeniya Malysheva")
public class ReqresInTests {

    String freeApiKeyName = "x-api-key",
            freeApiKeyValue = "reqres-free-v1";

    @Test
    @DisplayName("Запрос данных по несуществующему пользователю")
    void singleUserNotFound404Test() {
        int userId = 23;

        given()
                .header(freeApiKeyName, freeApiKeyValue)
                .log().uri()

                .when()
                .get("https://reqres.in/api/users/" + userId)

                .then()
                .log().status()
                .statusCode(404)
                .body(is("{}"));
    }

    @Test
    @DisplayName("Изменение только должности пользователя (через patch)")
    void successfulPatchUserJobTest() {
        int userId = 2;
        String newUserData = "{\"job\": \"zion resident\"}";

        given()
                .header(freeApiKeyName, freeApiKeyValue)
                .body(newUserData)
                .contentType(JSON)
                .log().uri()

                .when()
                .patch("https://reqres.in/api/users/" + userId)

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job", is("zion resident"));
    }

    @Test
    @DisplayName("Изменение должности пользователя через обновление всех полей записи (put)")
    void successfulUpdateUserJobTest() {
        int userId = 2;
        String newUserData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";

        given()
                .header(freeApiKeyName, freeApiKeyValue)
                .body(newUserData)
                .contentType(JSON)
                .log().uri()

                .when()
                .put("https://reqres.in/api/users/" + userId)

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void successfulCreateUserTest() {
        String userData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .header(freeApiKeyName, freeApiKeyValue)
                .body(userData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/users/")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    @Test
    @DisplayName("Удаление пользователя")
    void successfulDeleteUserTest() {
        int userId = 2;
        given()
                .header(freeApiKeyName, freeApiKeyValue)
                .contentType(JSON)
                .log().uri()

                .when()
                .delete("https://reqres.in/api/users/" + userId)

                .then()
                .log().status()
                .statusCode(204);
    }

    @Test
    @DisplayName("Неуспешная регистрация: отсутствует пароль")
    void errorMissingPasswordRegisterTest() {
        String userCredentials = "{\"email\": \"sydney@fife\"}";

        given()
                .header(freeApiKeyName, freeApiKeyValue)
                .body(userCredentials)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

}
