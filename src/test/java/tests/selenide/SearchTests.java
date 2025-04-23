package tests.selenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchTests {
    //тестируем git push
    @Test
    void newTest() {
        System.out.println("Not hello! Just Hi!");
    }

    @Test
    void successfulGoogleSearchTest() {
        open("https://www.google.com/");
        $("[name=q]").setValue("tests/selenide").pressEnter();
        $("[id=search]").shouldHave(text("https://selenide.org"));
    }

    @Test
    void successfulYaSearchTest() {
        open("https://www.ya.ru/");
        $("#text").setValue("tests/selenide").pressEnter();
        $("#search-result").shouldHave(text("selenide.org"));
    }

    @Test
    void successfulYahooSearchTest(){
        open("https://search.yahoo.com/");
        $("#yschsp").setValue("tests/selenide").pressEnter();
        $("div#results").shouldHave(text("selenide.org"));
    }

    @Test
    void successfulBingSearchTest(){
        open("https://www.bing.com/");
        $("[name=q]").setValue("tests/selenide").pressEnter();
        $("[id=b_results]").shouldHave(text("https://selenide.org"));
    }

    @Test
    void successfulDuckduckgoSearchTest() {
        open("https://duckduckgo.com/");
        $("#searchbox_input").setValue("tests/selenide").pressEnter();
        $(".react-results--main").shouldHave(text("https://selenide.org"));
    }

}
