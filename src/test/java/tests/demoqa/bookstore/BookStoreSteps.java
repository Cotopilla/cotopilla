package tests.demoqa.bookstore;

import io.qameta.allure.Step;
import models.demoqa.*;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;
import static specs.DemoqaSpecs.demoqaRequestSpec;
import static specs.DemoqaSpecs.demoqaResponseSpec;
import static tests.demoqa.bookstore.TestData.*;

public class BookStoreSteps {

    @Step("Авторизуемся на сайте")
    public LoginResponseModel demoqaAuth() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setUserName(DEMOQA_LOGIN);
        authData.setPassword(DEMOQA_PASSWORD);

        return given(demoqaRequestSpec)
                .body(authData)
                .when()
                .post(LOGIN_END_POINT)
                .then()
                .spec(demoqaResponseSpec)
                .statusCode(HTTP_OK)
                .extract().as(LoginResponseModel.class);
    }

    @Step("Подменяем cookie на сайте")
    public BookStoreSteps setDemoqaCookie
            (String userIdValue, String tokenValue, String expiresValue ) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", userIdValue));
        getWebDriver().manage().addCookie(new Cookie("expires", tokenValue));
        getWebDriver().manage().addCookie(new Cookie("token", expiresValue));

        return this;
    }

    @Step("Удаляем все книги из коллекции профиля")
    public BookStoreSteps deleteAllBooksFromProfileCollection
            (String userIdValue, String tokenValue) {
        given(demoqaRequestSpec)
                .header("Authorization", "Bearer " + tokenValue)
                .queryParams("UserId", userIdValue)
                .when()
                .delete(BOOKS_END_POINT)
                .then()
                .spec(demoqaResponseSpec)
                .statusCode(HTTP_NO_CONTENT);

        return this;
    }

    @Step("Удаляем одну книгу из коллекции профиля")
    public BookStoreSteps deleteOneBookFromProfileCollection
            (String isbnValue, String userIdValue, String tokenValue) {
        DeleteOneBookBodyModel deleteBookData = new DeleteOneBookBodyModel();
        deleteBookData.setUserId(userIdValue);
        deleteBookData.setIsbn(isbnValue);

        given(demoqaRequestSpec)
                .header("Authorization", "Bearer " + tokenValue)
                .body(deleteBookData)
                .when()
                .delete(BOOK_END_POINT)
                .then()
                .spec(demoqaResponseSpec)
                .statusCode(HTTP_NO_CONTENT);

        return this;
    }

    @Step("Добавляем книгу в коллекцию профиля")
    public BookStoreSteps addBooksToProfileCollection
            (String firstIsbn, String userIdValue, String tokenValue) {
        IsbnModel[] isbnArrayValue = {
                new IsbnModel(firstIsbn),
           };
        AddListOfBooksModel newBookData = new AddListOfBooksModel();
        newBookData.setUserId(userIdValue);
        newBookData.setCollectionOfIsbns(isbnArrayValue);

        given(demoqaRequestSpec)
                .header("Authorization", "Bearer " + tokenValue)
                .body(newBookData)
                .when()
                .post(BOOKS_END_POINT)
                .then()
                .spec(demoqaResponseSpec)
                .statusCode(HTTP_CREATED);

        return this;
    }

    @Step("Добавляем пару разных книг в коллекцию профиля")
    public BookStoreSteps addBooksToProfileCollection
            (String firstIsbn, String secondIsbn, String userIdValue, String tokenValue) {
        IsbnModel[] isbnArrayValue = {
                new IsbnModel(firstIsbn),
                new IsbnModel(secondIsbn)
        };
        AddListOfBooksModel newBookData = new AddListOfBooksModel();
        newBookData.setUserId(userIdValue);
        newBookData.setCollectionOfIsbns(isbnArrayValue);

        given(demoqaRequestSpec)
                .header("Authorization", "Bearer " + tokenValue)
                .body(newBookData)
                .when()
                .post(BOOKS_END_POINT)
                .then()
                .spec(demoqaResponseSpec)
                .statusCode(HTTP_CREATED);

        return this;
    }

    @Step("Проверяем, отображается ли книга")
    public void checkBookNameInProfileCollection(String bookName) {
        open("/profile");
        $(".rt-td a").shouldHave(text(bookName));
    }


}
