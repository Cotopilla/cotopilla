package tests.demoqa;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static tests.demoqa.TestData.*;

public class RegistrationWithPageObjectsTests extends TestBase {

    protected RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulAllFieldsRegistrationTest() {

        registrationPage.openRegistrationPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .setBirthDay(birthDaySent, birthMonthSent, birthYearSent)
                .setSubject(subjectName)
                .setHobby(hobbyName)
                .uploadPicture(filePath, fileName)
                .setAddress(address)
                .setStateAndCity(stateName, cityName)
                .submitForm()
                .checkResult();

    }

    @Test
    void successfulRequiredFieldRegistrationTest() {

        registrationPage.openRegistrationPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .submitForm()
                .checkResult();

    }

    @Test
    void checkErrorFirstNameTest() {

        registrationPage.openRegistrationPage()
                .checkBorderColor("first_name", "grey")
                .setLastName(lastName)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .submitErrorForm()
                .checkBorderColor("first_name", "red");

    }

    @Test
    void checkErrorEmailTest() {

        registrationPage.openRegistrationPage()
                .checkBorderColor("email", "grey")
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(" ")
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .submitErrorForm()
                .checkBorderColor("email", "red");

    }

    @Test
    void checkErrorPhoneNumberTest() {

        registrationPage.openRegistrationPage()
                .checkBorderColor("phone", "grey")
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setPhoneNumber("1")
                .submitErrorForm()
                .checkBorderColor("phone", "red");

    }

}