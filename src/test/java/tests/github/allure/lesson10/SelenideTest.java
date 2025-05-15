package tests.github.allure.lesson10;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

// from lesson 10
//package tests.github.allure;
//
//import com.codeborne.selenide.Condition;
//import com.codeborne.selenide.logevents.SelenideLogger;
//import io.qameta.allure.selenide.AllureSelenide;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
//
public class SelenideTest {

    @Test
    public void testIssueSearch() {
//        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").submit();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#80")).should(Condition.exist);
    }

}
