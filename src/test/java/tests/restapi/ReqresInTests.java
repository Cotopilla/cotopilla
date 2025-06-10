package tests.restapi;

import io.qameta.allure.Owner;
import models.lombok.reqresin.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.ReqresInSpecs.*;

@Owner("Evgeniya Malysheva")
public class ReqresInTests extends TestBase {

    int validUserId = 2;
    int notValidUserId = 23;

    @Test
    @DisplayName("Запрос данных по несуществующему пользователю")
    void singleUserNotFound404Test() {
        step("Make request for not exist user", () ->
                given(requestSpec)

                        .when()
                        .get(USERS_END_POINT + notValidUserId)

                        .then()
                        .spec(error404ResponseSpec)
                        .body(is("{}")));
    }

    @Test
    @DisplayName("Изменение данных пользователя (через patch)")
    void successfulPatchUserJobTest() {
        CreateAndUpdateBodyModel updateUserDataBody = new CreateAndUpdateBodyModel();
        updateUserDataBody.setName("morpheus");
        updateUserDataBody.setJob("zion resident");

        UpdateResponseModel response = step("Make update (PATCH) request", () ->
                given(requestSpec)
                        .body(updateUserDataBody)

                        .when()
                        .patch(USERS_END_POINT + validUserId)

                        .then()
                        .spec(updateResponseSpec)
                        .extract().as(UpdateResponseModel.class));

        step("Check response", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("zion resident", response.getJob());
        });
    }

    @Test
    @DisplayName("Изменение данных пользователя (через put)")
    void successfulUpdateUserJobTest() {
        CreateAndUpdateBodyModel updateUserDataBody = new CreateAndUpdateBodyModel();
        updateUserDataBody.setName("morpheus");
        updateUserDataBody.setJob("zion resident");

        UpdateResponseModel response = step("Make update (PUT) request", () ->
                given(requestSpec)
                        .body(updateUserDataBody)

                        .when()
                        .put(USERS_END_POINT + validUserId)

                        .then()
                        .spec(updateResponseSpec)
                        .extract().as(UpdateResponseModel.class));

        step("Check response", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("zion resident", response.getJob());
        });
    }

    @Test
    @DisplayName("Успешное создание пользователя")
    void successfulCreateUserTest() {
        CreateAndUpdateBodyModel newUserDataBody = new CreateAndUpdateBodyModel();
        newUserDataBody.setName("morpheus");
        newUserDataBody.setJob("zion resident");

        CreateResponseModel response = step("Make create request", () ->
                given(requestSpec)
                        .body(newUserDataBody)

                        .when()
                        .post(USERS_END_POINT)

                        .then()
                        .spec(createResponseSpec)
                        .extract().as(CreateResponseModel.class));

        step("Check response", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("zion resident", response.getJob());
        });
    }

    @Test
    @DisplayName("Удаление пользователя")
    void successfulDeleteUserTest() {
        given(requestSpec)

                .when()
                .delete(USERS_END_POINT + validUserId)

                .then()
                .spec(deleteResponseSpec);
    }

    @Test
    @DisplayName("Неуспешная регистрация: отсутствует пароль")
    void errorMissingPasswordRegisterTest() {
        RegisterBodyModel userCredentials = new RegisterBodyModel();
        userCredentials.setEmail("eve.holt@reqres.in");

        RegisterErrorResponseModel response = step("Make unsuccessful register request", () ->
                given(requestSpec)
                        .body(userCredentials)

                        .when()
                        .post(REGISTER_END_POINT)

                        .then()
                        .spec(error400ResponseSpec)
                        .extract().as(RegisterErrorResponseModel.class));

        step("Check unsuccessful response", () ->
                assertEquals("Missing password", response.getError()));
    }
}
