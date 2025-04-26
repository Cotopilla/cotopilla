package tests.demoqa;

import org.junit.jupiter.api.Test;


public class RegistrationWithPageObjectsTests extends TestBase {
    private String filePath = "img/";
    private String fileName = "category_flowers.jpg";
    private String firstName = "Evgeniya";
    private String lastName = "Malysheva";
    private String email = "email@yandex.ru";
    private String gender = "Female";
    private String phoneNumber = "1234567890";
    private String birthDaySent = "30";
    private String birthMonthSent = "August";
    private String birthYearSent = "1987";
    private String subjectName = "Maths";
    private String hobbyName = "Sports";
    private String address = "Bla bla street 1";
    private String stateName = "Uttar Pradesh";
    private String cityName = "Lucknow";

    @Test
    void successfulAllFieldsRegistrationTest() {

        registrationPage.openRegistrationPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .setBirthDay(birthDaySent,birthMonthSent,birthYearSent)
                .setSubject(subjectName)
                .setHobby(hobbyName)
                .uploadPicture(filePath, fileName)
                .setAddress(address)
                .setStateAndCity(stateName,cityName)
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
                .checkBorderColor("first_name","grey")
                .setLastName(lastName)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .submitErrorForm()
                .checkBorderColor("first_name","red");

    }

    @Test
    void checkErrorEmailTest() {

        registrationPage.openRegistrationPage()
                .checkBorderColor("email","grey")
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(" ")
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .submitErrorForm()
                .checkBorderColor("email","red");

    }

    @Test
    void checkErrorPhoneNumberTest() {

        registrationPage.openRegistrationPage()
                .checkBorderColor("phone","grey")
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setPhoneNumber("1")
                .submitErrorForm()
                .checkBorderColor("phone","red");

    }

}