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
    void successfulRegistrationTest() {

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
}