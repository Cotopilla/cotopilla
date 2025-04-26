package tests.demoqa;

import org.junit.jupiter.api.Test;


public class RegistrationWithPageObjectsTests extends TestBase {
    private final String filePath = "img/",
            fileName = "category_flowers.jpg",
            firstName = "Evgeniya",
            lastName = "Malysheva",
            email = "email@yandex.ru",
            gender = "Female",
            phoneNumber = "1234567890",
            birthDaySent = "30",
            birthMonthSent = "August",
            birthYearSent = "1987",
            subjectName = "Maths",
            hobbyName = "Sports",
            address = "Bla bla street 1",
            stateName = "Uttar Pradesh",
            cityName = "Lucknow";

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