import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {
    private String fileName = "category_flowers.jpg";
    private String firstName = "Evgeniya";
    private String lastName = "Malysheva";
    private String email = "cotopilla@yandex.ru";
    private String gender = "Female";
    private String phoneNumber = "9209209209";
//    private String dateOfBirthSent = "30 Aug 1987";
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
    void fillAllForm() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");   //убираем всплывающие баннеры, чтобы не возникла
        executeJavaScript("$('footer').remove()");      //ошибка element click intercepted

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").find(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);

//     Установка даты рождения (вариант №1)
//        $("#dateOfBirthInput").click();
//        $("#dateOfBirthInput").sendKeys(Keys.CONTROL + "a");
//        $("#dateOfBirthInput").sendKeys(dateOfBirthSent);
//        $("#dateOfBirth-label").click();

//     Установка даты рождения (вариант №2)
        $(".react-datepicker-wrapper").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirthSent);
        $(".react-datepicker__year-select").selectOption(yearOfBirthSent);
        //отсекаем даты предыдущего месяца
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text(dayOfBirthSent)).click();

        $("#subjectsInput").sendKeys(subjectName);
        $("#subjectsInput").pressEnter();
        $("#hobbiesWrapper").scrollTo();
        $("#hobbiesWrapper").find(byText(hobbyName)).click(); //find=$, findAll=$$
        $("#uploadPicture").uploadFromClasspath(fileName);
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(stateName)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(cityName)).click();
        $("#submit").click();

        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text(email));
        $(".table-responsive").shouldHave(text(gender));
        $(".table-responsive").shouldHave(text(phoneNumber));
        $(".table-responsive").shouldHave(text(dateOfBirthReceived));
        $(".table-responsive").shouldHave(text(subjectName));
        $(".table-responsive").shouldHave(text(hobbyName));
        $(".table-responsive").shouldHave(text(fileName));
        $(".table-responsive").shouldHave(text(address));
        $(".table-responsive").shouldHave(text(stateName + " " + cityName));

        $("#closeLargeModal").sendKeys(Keys.END);
        $("#closeLargeModal").click();
    }
}
