package tests.demoqa;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import models.demoqa.LoginBodyModel;
import models.demoqa.LoginResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static specs.DemoqaSpecs.demoqaRequestSpec;
import static specs.DemoqaSpecs.demoqaResponseSpec;
import static tests.demoqa.TestData.demoqaLogin;
import static tests.demoqa.TestData.demoqaPassword;

public class TestBase {

    @BeforeAll
    static void setBrowserParams() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void closeDriver() {
        closeWebDriver();
    }

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
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }

    public void setDemoqaCookie(LoginResponseModel authResponse) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
    }
}
