package tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Tag("property")
public class RegistrationRemotePropertyTests {

    protected RegistrationPage registrationPage = new RegistrationPage();
    protected TestData testData = new TestData();

    @BeforeAll
    static void setBrowserParams() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("version");
        Configuration.browserSize = System.getProperty("resolution");
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://"
                + System.getProperty("selenoid")
                + "/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void getAttachAndCloseDriver() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

    @Test
    void successfulAllFieldsRegistrationTest() {

        registrationPage.openRegistrationPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.email)
                .setGender(testData.gender)
                .setPhoneNumber(testData.phoneNumber)
                .setBirthDay(testData.birthDaySent, testData.birthMonthSent, testData.birthYearSent)
                .setSubject(testData.subjectName)
                .setHobby(testData.hobbyName)
                .uploadPicture(testData.filePath, testData.fileName)
                .setAddress(testData.address)
                .setStateAndCity(testData.stateName, testData.cityName)
                .submitForm()
                .checkResult();

    }

    @Test
    void successfulRequiredFieldRegistrationTest() {

        registrationPage.openRegistrationPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setPhoneNumber(testData.phoneNumber)
                .submitForm()
                .checkResult();

    }

    @Test
    void checkErrorFirstNameTest() {

        registrationPage.openRegistrationPage()
                .checkBorderColor("first_name", "grey")
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setPhoneNumber(testData.phoneNumber)
                .submitErrorForm()
                .checkBorderColor("first_name", "red");

    }

    @Test
    void checkErrorEmailTest() {

        registrationPage.openRegistrationPage()
                .checkBorderColor("email", "grey")
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(" ")
                .setGender(testData.gender)
                .setPhoneNumber(testData.phoneNumber)
                .submitErrorForm()
                .checkBorderColor("email", "red");

    }

    @Test
    void checkErrorPhoneNumberTest() {

        registrationPage.openRegistrationPage()
                .checkBorderColor("phone", "grey")
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setPhoneNumber("1")
                .submitErrorForm()
                .checkBorderColor("phone", "red");

    }

}