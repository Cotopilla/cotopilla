package tests.demoqa;

import io.qameta.allure.Step;
import models.demoqa.*;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;
import static specs.DemoqaSpecs.demoqaRequestSpec;
import static specs.DemoqaSpecs.demoqaResponseSpec;
import static tests.demoqa.TestData.demoqaLogin;
import static tests.demoqa.TestData.demoqaPassword;

public class BookStoreSteps {

    @Step("Авторизуемся на сайте")
    public LoginResponseModel demoqaAuth() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setUserName(demoqaLogin);
        authData.setPassword(demoqaPassword);

        return given(demoqaRequestSpec)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(demoqaResponseSpec)
                .statusCode(HTTP_OK)
                .extract().as(LoginResponseModel.class);
    }

    @Step("Подменяем cookie на сайте")
    public BookStoreSteps setDemoqaCookie(LoginResponseModel authResponse) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));

        return this;
    }

    @Step("Удаляем все книги из коллекции профиля")
    public BookStoreSteps deleteAllBooksFromProfileCollection(String userIdValue, String tokenValue) {
        given(demoqaRequestSpec)
                .header("Authorization", "Bearer " + tokenValue)
                .queryParams("UserId", userIdValue)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(demoqaResponseSpec)
                .statusCode(HTTP_NO_CONTENT);

        return this;
    }

    @Step("Удаляем одну книгу из коллекции профиля")
    public BookStoreSteps deleteOneBookFromProfileCollection
            (String isbnValue, String userIdValue, String tokenValue) {
        AddOrDeleteOneBookBodyModel deleteBookData = new AddOrDeleteOneBookBodyModel();
        deleteBookData.setUserId(userIdValue);
        deleteBookData.setIsbn(isbnValue);

        given(demoqaRequestSpec)
                .header("Authorization", "Bearer " + tokenValue)
                .body(deleteBookData)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(demoqaResponseSpec)
                .statusCode(HTTP_NO_CONTENT);

        return this;
    }

    @Step("Добавляем книгу в коллекцию профиля")
    public BookStoreSteps addBookToProfileCollection
            (String isbnValue, String userIdValue, String tokenValue) {
        AddOrDeleteOneBookBodyModel newBookData = new AddOrDeleteOneBookBodyModel();
        newBookData.setUserId(userIdValue);
        newBookData.setIsbn(isbnValue);

        given(demoqaRequestSpec)
                .header("Authorization", "Bearer " + tokenValue)
                .body(newBookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(demoqaResponseSpec)
                .statusCode(HTTP_CREATED);

        return this;
    }

    @Step("Добавляем несколько книг в коллекцию профиля")
    public BookStoreSteps addSomeBooksToProfileCollection
            (String firstIsbn, String secondIsbn, String userIdValue, String tokenValue) {
        IsbnModel[] isbnArrayValue = {
                new IsbnModel(firstIsbn),
                new IsbnModel(secondIsbn)
        };
        AddListOfBooksModel bookData = new AddListOfBooksModel();
        bookData.setUserId(userIdValue);
        bookData.setCollectionOfIsbns(isbnArrayValue);

        given(demoqaRequestSpec)
                .header("Authorization", "Bearer " + tokenValue)
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(demoqaResponseSpec)
                .statusCode(HTTP_CREATED);

        return this;
    }

    @Step("Проверяем, отображается ли книга")
    public BookStoreSteps checkBookNameInProfileCollection(String bookName) {
        open("/profile");
        $(".ReactTable").shouldHave(text(bookName));

        return this;
    }


}
