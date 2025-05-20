package pages;

import pages.components.CalendarComponent;
import com.codeborne.selenide.SelenideElement;
import pages.components.ModalDialogComponent;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName"),
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


    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final ModalDialogComponent modalDialogComponent = new ModalDialogComponent();
    private final ArrayList<String> arrayList = new ArrayList<>();

    public RegistrationPage openRegistrationPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        removeBanners();
        return this;
    }

    public void removeBanners() {
        executeJavaScript("$('#fixedban').remove()");   //убираем всплывающие баннеры, чтобы не возникла
        executeJavaScript("$('footer').remove()");      //ошибка element click intercepted для кнопки Submit
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
        uploadPictureButton.uploadFromClasspath(filePath + fileName);
        arrayList.add(fileName);

        return this;
    }

    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);
        arrayList.add(value);

        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        stateInput.scrollIntoView(true).click();
        stateCityWrapper.$(byText(state)).click();
        cityInput.click();
        stateCityWrapper.$(byText(city)).click();
        arrayList.add(state + " " + city);

        return this;
    }

    public RegistrationPage submitForm() {
        $("#state").scrollIntoView(true);
        submitButton.scrollIntoView(true).click();
        modalDialogComponent.waitingForm();

        return this;
    }

    public void checkResult() {
        arrayList.forEach(x -> resultTable.shouldHave(text(x)));
    }

//    negative tests methods

    public RegistrationPage submitErrorForm() {
        submitButton.click();
        modalDialogComponent.notAppearingForm();

        return this;
    }

    public RegistrationPage checkBorderColor(String field, String color) {
        String cssPropertyName = "border-color",
                redColor = "rgb(220, 53, 69)",
                greyColor = "rgb(206, 212, 218)";
        String expectedColor;
        if (color.equals("red")) {
            expectedColor = redColor;
        } else if (color.equals("grey")) {
            expectedColor = greyColor;
        } else throw new IllegalArgumentException("Неверно задан цвет");

        switch (field) {
            case "first_name":
                firstNameInput.shouldHave(cssValue(cssPropertyName, expectedColor));
                break;
            case "email":
                emailInput.shouldHave(cssValue(cssPropertyName, expectedColor));
                break;
            case "phone":
                phoneNumberInput.shouldHave(cssValue(cssPropertyName, expectedColor));
                break;
            default:
                throw new IllegalArgumentException("Неверно указано название поля ввода");
        }

        return this;

    }

}
