package tests.demoqa;

import static utils.RandomUtils.*;

public class TestData {

    protected String firstName = getRandomFirstName(),
            lastName = getRandomLastName(),
            email = getRandomEmailAddress(),
            gender = getRandomGender(),
            phoneNumber = getRandomPhoneNumber(),
            birthDaySent = getRandomBirthDay(),
            birthMonthSent = getRandomBirthMonth(),
            birthYearSent = getRandomBirthYear(),
            subjectName = getRandomSubject(),
            hobbyName = getRandomHobby(),
            address = getRandomAddress(),
            stateName = getRandomState(),
            cityName = getRandomCity(stateName),
            filePath = getFilePath(),
            fileName = getFileName();

    protected final static String DEMOQA_LOGIN = "EMalysh",
            DEMOQA_PASSWORD = "Vbrd_100)%",
            BOOK_END_POINT = "/BookStore/v1/Book",
            BOOKS_END_POINT = "/BookStore/v1/Books",
            LOGIN_END_POINT = "/Account/v1/Login";


}

