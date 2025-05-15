package tests.github.allure;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebStepsIssueNameTest {

    @Step("Открываем главную страницу github.com")
    public WebStepsIssueNameTest openTheMainPage(){
        open("https://github.com/");

        return this;
    }

    @Step("Вводим в строку поиска {repo}")
    public WebStepsIssueNameTest fillTheSearchBar(String repo){
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").pressEnter();

        return this;
    }

    @Step("Переходим в репозиторий {repo}")
    public WebStepsIssueNameTest moveToRepository(String repo){
            $(By.linkText(repo)).click();

        return this;
     }

    @Step("Проверяем видимость элемента Issue и кликаем на него")
    public WebStepsIssueNameTest checkAndClickToIssueTab(){
        $("#issues-tab").shouldBe(visible).click();

        return this;
    }

    @Step("Ищем Issue с названием Dark Theme Support")
    public void findIssueByName(){
        $("[data-listview-component='items-list']").
                shouldHave(text("Dark Theme Support"));

    }

}
