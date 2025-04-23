package tests.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests {
    private String filePath = "img/";
    private String fileName = "category_flowers.jpg";
    private String firstName = "Evgeniya";
    private String lastName = "Malysheva";
    private String email = "cotopilla@yandex.ru";
    private String gender = "Female";
    private String phoneNumber = "9209209209";
    private String monthOfBirthSent = "August";
    private String yearOfBirthSent = "1987";
    private String dayOfBirthSent = "30";
    private String dateOfBirthReceived = "30 August,1987";
    private String subjectName = "Maths";
    private String hobbyName = "Sports";
    private String address = "Blabla Street";
    private String stateName = "Uttar Pradesh";
    private String cityName = "Lucknow";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    void successfulRegistrationTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");   //убираем всплывающие баннеры, чтобы не возникла
        executeJavaScript("$('footer').remove()");      //ошибка element click intercepted

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirthSent);
        $(".react-datepicker__year-select").selectOption(yearOfBirthSent);
        //отсекаем даты предыдущего месяца
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text(dayOfBirthSent)).click();
        $("#subjectsInput").sendKeys(subjectName);
        $("#subjectsInput").pressEnter();
        $("#hobbiesWrapper").scrollTo();
        $("#hobbiesWrapper").$(byText(hobbyName)).click(); //find=$, findAll=$$
        $("#uploadPicture").uploadFromClasspath(filePath+fileName);
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(stateName)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(cityName)).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName + " " + lastName),
                text(email), text(gender), text(phoneNumber), text(dateOfBirthReceived),
                text(subjectName), text(hobbyName), text(fileName), text(address),
                text(stateName + " " + cityName));

//        $("#closeLargeModal").sendKeys(Keys.END);
//        $("#closeLargeModal").click();
    }
}