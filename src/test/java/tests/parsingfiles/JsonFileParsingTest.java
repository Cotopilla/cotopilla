package tests.parsingfiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.parsingfiles.model.Glossary;
import tests.parsingfiles.model.School;
import tests.parsingfiles.model.SchoolAddress;

import java.io.InputStreamReader;
import java.io.Reader;

public class JsonFileParsingTest {

    private ClassLoader cl = FilesParsingTest.class.getClassLoader();
    private static final Gson gson = new Gson();

    @Test
    void schoolGsonWithoutModelFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("parsingfiles/school.json")
        )) {
            JsonObject school = gson.fromJson(reader, JsonObject.class);
            Assertions.assertTrue(school.get("name").getAsString().contains("Школа №110"));
            Assertions.assertEquals(110, school.get("number").getAsInt());

            JsonObject address = school.get("address").getAsJsonObject();
            Assertions.assertEquals("Нижний Новгород", address.get("City").getAsString());

            JsonArray array = school.get("class_name").getAsJsonArray();
            Assertions.assertEquals(4, array.size());
            Assertions.assertEquals("5Г", array.get(3).getAsString());
        }
    }

    @Test
    void schoolGsonWithModelFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("parsingfiles/school.json")
        )) {
            School school = gson.fromJson(reader, School.class);
            Assertions.assertTrue(school.getName().contains("Школа №110"));
            Assertions.assertEquals(110, school.getNumber());

            SchoolAddress address = school.getAddress();
            Assertions.assertEquals("Нижний Новгород", address.getCity());

            String[] array = school.getClassName();
            Assertions.assertEquals(4, array.length);
            Assertions.assertEquals("5Г", array[3]);
        }
    }

    @Test
    void schoolJacksonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("parsingfiles/school.json")
        )) {
            ObjectMapper json = new ObjectMapper();
            School school = json.readValue(reader, School.class);

            Assertions.assertTrue(school.getName().contains("Школа №110"));
            Assertions.assertEquals(110, school.getNumber());

            SchoolAddress address = school.getAddress();
            Assertions.assertEquals("Нижний Новгород", address.getCity());

            String[] classesName = school.getClassName();
            Assertions.assertEquals(4, classesName.length);
            Assertions.assertEquals("5Г", classesName[3]);
        }
    }

    //from lesson 9
    @Test
    void gsonWithoutModelFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("parsingfiles/glossary.json")
        )) {
            JsonObject actual = gson.fromJson(reader, JsonObject.class);

            Assertions.assertEquals("example glossary", actual.get("title").getAsString());
            Assertions.assertEquals(234234, actual.get("ID").getAsInt());

            JsonObject inner = actual.get("glossary").getAsJsonObject();

            Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
            Assertions.assertEquals("Standard Generalized Markup Language", inner.get("GlossTerm").getAsString());
        }
    }

    @Test
    void gsonWithModelFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("parsingfiles/glossary.json")
        )) {
            Glossary actual = gson.fromJson(reader, Glossary.class);

            Assertions.assertEquals("example glossary", actual.getTitle());
            Assertions.assertEquals(234234, actual.getID());
            Assertions.assertEquals("SGML", actual.getGlossary().getSortAs());
            Assertions.assertEquals("Standard Generalized Markup Language", actual.getGlossary().getGlossTerm());
        }
    }
}
