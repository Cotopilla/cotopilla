import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchTests {
    //тестируем git push
    @Test
    void newTest() {
        System.out.println("Hello");
    }

    @Test
    void successfulGoogleSearchTest() {
        open("https://www.google.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("[id=search]").shouldHave(text("https://selenide.org"));
    }

    @Test
    void successfulYaSearchTest() {
        open("https://www.ya.ru/");
        $("#text").setValue("selenide").pressEnter();
        $("#search-result").shouldHave(text("selenide.org"));
    }

    @Test
    void successfulYahooSearchTest(){
        open("https://search.yahoo.com/");
        $("#yschsp").setValue("selenide").pressEnter();
        $("div#results").shouldHave(text("selenide.org"));
    }

    @Test
    void successfulBingSearchTest(){
        open("https://www.bing.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("[id=b_results]").shouldHave(text("https://selenide.org"));
    }

    @Test
    void successfulDuckduckgoSearchTest() {
        open("https://duckduckgo.com/");
        $("#searchbox_input").setValue("selenide").pressEnter();
        $(".react-results--main").shouldHave(text("https://selenide.org"));
    }

}
