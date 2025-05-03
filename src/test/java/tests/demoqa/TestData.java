package tests.demoqa;

import static utils.RandomUtils.*;

public class TestData {

    public static final String firstName = getRandomFirstName(),
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
