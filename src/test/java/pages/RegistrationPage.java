package pages;

import pages.components.CalendarComponent;
import com.codeborne.selenide.SelenideElement;
import pages.components.ModalDialogComponent;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            phoneNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPictureButton = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("#state"),
            stateCityWrapper = $("#stateCity-wrapper"),
            cityInput = $("#city"),
            submitButton = $("#submit"),
            resultTable = $(".table-responsive");

    CalendarComponent calendarComponent = new CalendarComponent();
    ModalDialogComponent modalDialogComponent = new ModalDialogComponent();
    ArrayList<String> arrayList = new ArrayList<>();

    public RegistrationPage openRegistrationPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");   //убираем всплывающие баннеры, чтобы не возникла
        executeJavaScript("$('footer').remove()");      //ошибка element click intercepted для кнопки Submit

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        arrayList.add(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        arrayList.add(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);
        arrayList.add(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        arrayList.add(value);

        return this;
    }

    public RegistrationPage setPhoneNumber(String value) {
        phoneNumberInput.setValue(value);
        arrayList.add(value);

        return this;
    }

    public RegistrationPage setBirthDay(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        arrayList.add(day + " " + month + "," + year);

        return this;
    }

    public RegistrationPage setSubject(String value) {
        subjectsInput.sendKeys(value);
        subjectsInput.pressEnter();
        arrayList.add(value);

        return this;
    }

    public RegistrationPage setHobby(String value) {
        hobbiesWrapper.$(byText(value)).click();
        arrayList.add(value);

        return this;
    }

    public RegistrationPage uploadPicture(String filePath, String fileName) {
        uploadPictureButton.uploadFromClasspath(filePath+fileName);
        arrayList.add(fileName);

        return this;
    }

    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);
        arrayList.add(value);

        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        stateInput.click();
        stateCityWrapper.$(byText(state)).click();
        cityInput.click();
        stateCityWrapper.$(byText(city)).click();
        arrayList.add(state + " " + city);

        return this;
    }

//    public ModalDialogComponent submitForm() {
//        submitButton.click();
//
//        return modalDialogComponent;
//    }

    public RegistrationPage submitForm() {
        submitButton.click();
        modalDialogComponent.waitingForm();

        return this;
    }

    public RegistrationPage checkResult() {
        checkResult(arrayList);

        return this;
    }

    public RegistrationPage checkResult(List<String> list) {
        for(String value: list){
            resultTable.shouldHave(text(value));
        }

        return this;

    }


}
