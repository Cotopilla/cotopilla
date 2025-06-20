package tests.demoqa.registration;

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
}

