import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {
    private static String pathName = "C:\\Users\\yevgeniya.malysheva\\Downloads\\";
    private static String fileName = "category_flowers.jpg";
    private static String firstName = "Evgeniya";
    private static String lastName = "Malysheva";
    private static String email = "cotopilla@yandex.ru";
    private static String gender = "Female";
    private static String phoneNumber = "9209209209";
    private static String dateOfBirthSent = "31 Jan 2013";
    private static String dateOfBirthReceived = "31 January,2013";
    private static String subjectName = "Maths";
    private static String hobbyName = "Sports, Reading";
    private static String address = "Blabla Street";
    private static String stateAndCity = "Uttar Pradesh Lucknow";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
            }

    @Test
    void fillAllForm() {
        File fileUpload = new File(pathName+fileName);
        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[for='gender-radio-2']").shouldBe(Condition.visible).click();
        $("#dateOfBirthInput").click();
        $("#dateOfBirthInput").sendKeys(Keys.CONTROL + "a");
        $("#dateOfBirthInput").sendKeys(dateOfBirthSent);
        $("#dateOfBirth-label").click();
        $("#userNumber").setValue(phoneNumber);
        $("#subjectsInput").sendKeys(subjectName);
        $("#subjectsInput").pressEnter();
        $("[for=hobbies-checkbox-1]").scrollTo();
        $("[for=hobbies-checkbox-1]").shouldBe(Condition.visible).click();
        $("[for=hobbies-checkbox-2]").shouldBe(Condition.visible).click();
        $("#uploadPicture").uploadFile(fileUpload);
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#react-select-3-option-1").shouldBe(Condition.visible).click();
        $("#city").click();
        $("#react-select-4-option-1").shouldBe(Condition.visible).click();
        $("#submit").click();

        Selenide.switchTo().activeElement();
        $(byText(firstName+" "+lastName)).should(exist);
        $(byText(email)).should(exist);
        $(byText(gender)).should(exist);
        $(byText(phoneNumber)).should(exist);
        $(byText(dateOfBirthReceived)).should(exist);
        $(byText(subjectName)).should(exist);
        $(byText(hobbyName)).should(exist);
        $(byText(fileName)).should(exist);
        $(byText(address)).should(exist);
        $(byText(stateAndCity)).should(exist);

        $("#closeLargeModal").sendKeys(Keys.END);
        $("#closeLargeModal").click();

        closeWebDriver();
    }
}
