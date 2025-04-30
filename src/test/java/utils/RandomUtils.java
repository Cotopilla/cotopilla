package utils;

import com.codeborne.selenide.conditions.Value;
import com.github.javafaker.Faker;

import static java.lang.String.valueOf;

public class RandomUtils {
    private static Faker faker = new Faker();

    public static String getRandomItemFromList(String[] listOfItems) {
        int randomNumber = faker.number().numberBetween(0, listOfItems.length);  //min - inclusive, max - exclusive (unless min == max)

        return listOfItems[randomNumber];
    }

    public static String getRandomGender() {
        String[] genderList = {"Male", "Female", "Other"};

        return getRandomItemFromList(genderList);
    }

    public static String getRandomHobby() {
        String[] hobbyList = {"Sports", "Reading", "Music"};

        return getRandomItemFromList(hobbyList);
    }

    public static String getRandomPhoneNumber() {

        return faker.phoneNumber().cellPhone()
                .replaceAll("[^0-9]", "")
                .substring(0, 10);
    }

    public static String getRandomBirthDay() {

        return valueOf(faker.number().numberBetween(1, 32));
    }

    public static String getRandomBirthMonth() {
        String[] monthList = {"January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"};

        return getRandomItemFromList(monthList);
    }

    public static String getRandomBirthYear() {

        return valueOf(faker.number().numberBetween(1950, 2025));
    }

    public static String getRandomSubject() {
        String[] subjectList = {"Maths", "Chemistry", "Computer Science",
                "Commerce", "Economics"};

        return getRandomItemFromList(subjectList);
    }

//    public static String getRandomState() {
//        String[] stateList = {"NCR", "Uttar Pradesh", "Haryana",
//                "Rajasthan"};
//
//        return getRandomItemFromList(stateList);
//    }

    public static String getRandomCity() {
        String[] cityList = {"Delhi", "Gurgaon", "Noida"};

        return getRandomItemFromList(cityList);
    }

}
