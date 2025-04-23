package tests.demoqa;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationWithPageObjectsTests extends TestBase {
    private String filePath = "img/";
    private String fileName = "category_flowers.jpg";
    private String firstName = "Evgeniya";
    private String lastName = "Malysheva";
    private String email = "email@yandex.ru";
    private String gender = "Female";
    private String phoneNumber = "1234567890";
    private String dateOfBirthReceived = "30 August,1987";
    private String subjectName = "Maths";
    private String hobbyName = "Sports";
    private String address = "Bla bla street 1";
    private String stateName = "Uttar Pradesh";
    private String cityName = "Lucknow";

    @Test
    void successfulRegistrationTest() {

        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openRegistrationPage()
                .setFirstName("Evgeniya")
                .setLastName("Malysheva")
                .setEmail("email@yandex.ru")
                .setGender("Female")
                .setPhoneNumber("1234567890")
                .setBirthDay("30","August","1987")
                .setAddress("Bla bla street 1");

        $("#subjectsInput").sendKeys(subjectName);
        $("#subjectsInput").pressEnter();
        $("#hobbiesWrapper").scrollTo();
        $("#hobbiesWrapper").$(byText(hobbyName)).click(); //find=$, findAll=$$
        $("#uploadPicture").uploadFromClasspath(filePath + fileName);

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

    }
}