package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

import static java.lang.String.valueOf;

public class RandomUtils {
    private static final Faker faker = new Faker(new Locale("en"));

    public static String getRandomFirstName() {

        return faker.name().firstName();
    }

    public static String getRandomLastName() {

        return faker.name().lastName();
    }

    public static String getRandomEmailAddress() {

        return faker.internet().emailAddress();
    }

    public static String getRandomGender() {

        return faker.options().option("Male", "Female", "Other");
    }

    public static String getRandomPhoneNumber() {

        return faker.phoneNumber().cellPhone()
                .replaceAll("[^0-9]", "")
                .substring(0, 10);
    }

    public static String getRandomBirthDay() {

        return valueOf(faker.number().numberBetween(1, 28));
    }

    public static String getRandomBirthMonth() {

        return faker.options().option("January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December");
    }

    public static String getRandomBirthYear() {

        return valueOf(faker.number().numberBetween(1950, 2025));
    }

    public static String getRandomSubject() {

        return faker.options().option("Maths", "Chemistry", "Computer Science",
                "Commerce", "Economics");
    }

    public static String getRandomHobby() {

        return faker.options().option("Sports", "Reading", "Music");
    }

    public static String getFilePath() {

        return faker.options().option("img/");
    }

    public static String getFileName() {

        return faker.options().option("category_flowers.jpg");
    }

    public static String getRandomAddress() {

        return faker.address().fullAddress();
    }

    public static String getRandomState() {

        return faker.options().option("NCR", "Uttar Pradesh", "Haryana",
                "Rajasthan");
    }

    public static String getRandomCity(String stateName) {
        String cityName = null;
        switch (stateName) {
            case "NCR":
                cityName = faker.options().option("Delhi", "Gurgaon", "Noida");
                break;
            case "Uttar Pradesh":
                cityName = faker.options().option("Agra", "Lucknow", "Merrut");
                break;
            case "Haryana":
                cityName = faker.options().option("Karnal", "Panipat");
                break;
            case "Rajasthan":
                cityName = faker.options().option("Jaipur", "Jaiselmer");
                break;
        }

        return cityName;
    }

}
