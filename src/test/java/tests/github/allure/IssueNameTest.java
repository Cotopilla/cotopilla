package tests.github.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class IssueNameTest {

    private static final String REPOSITORY = "qa-guru/allure-notifications";

    @AfterEach
    void closeDriver() {
        closeWebDriver();
    }

    @Test
    public void listenerIssueNameCheckTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com/");

        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("qa-guru/allure-notifications");
        $("#query-builder-test").pressEnter();

        $(By.linkText("qa-guru/allure-notifications")).click();
        $("#issues-tab").shouldBe(visible).click();
        $("[data-listview-component='items-list']").shouldHave(text("Dark Theme Support"));
    }

    @Test
    public void lambdaIssueNameCheckTest() {

        step("Открываем главную страницу github.com", () -> {
            open("https://github.com/");
        });

        step("Вводим в строку поиска " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").pressEnter();
        });

        step("Переходим в репозиторий " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Проверяем видимость элемента Issue и кликаем на него", () -> {
            $("#issues-tab").shouldBe(visible).click();
        });

        step("Ищем Issue с названием Dark Theme Support", () -> {
            $("[data-listview-component='items-list']").
                    shouldHave(text("Dark Theme Support"));
        });
    }

    @Test
    public void stepsIssueNameCheckTest() {
        WebStepsIssueNameTest webSteps = new WebStepsIssueNameTest();

        webSteps.openTheMainPage()
                .fillTheSearchBar(REPOSITORY)
                .moveToRepository(REPOSITORY)
                .checkAndClickToIssueTab()
                .findIssueByName();
    }
}
