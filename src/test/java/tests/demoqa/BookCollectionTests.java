package tests.demoqa;

import models.demoqa.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;

public class BookCollectionTests extends TestBase {

    @Test
    void addBookToCollectionTest() {

        LoginResponseModel authResponse =
                step("Авторизуемся на сайте", this::demoqaAuth);

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .queryParams("UserId", authResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);

        String isbn = "9781449365035";
        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                authResponse.getUserId(), isbn);

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);


        step("Подменяем cookie на сайте", () -> {
            setDemoqaCookie(authResponse);
        });

        step("Проверяем, отображается ли книга", () -> {
            open("/profile");
            $(".ReactTable").shouldHave(text("Speaking JavaScript"));
        });
    }

    @Test
    void addBookToCollection_withDelete1Book_Test() {
        LoginResponseModel authResponse =
                step("Авторизуемся на сайте", this::demoqaAuth);

        String isbn = "9781449365035";
        String deleteBookData = format("{\"userId\":\"%s\",\"isbn\":\"%s\"}",
                authResponse.getUserId(), isbn);

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(deleteBookData)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);

        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                authResponse.getUserId(), isbn);

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);

        step("Подменяем cookie на сайте", () -> {
            setDemoqaCookie(authResponse);
        });

        step("Проверяем, отображается ли книга", () -> {
            open("/profile");
            $(".ReactTable").shouldHave(text("Speaking JavaScript"));
        });
    }
}
