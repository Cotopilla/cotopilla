package tests.litres;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LitresTests {

    @BeforeEach
    void setUp() {
        open("https://www.litres.ru/");
        Configuration.pageLoadStrategy = "eager";
    }

    @ValueSource(strings = {
            "python", "java"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список книг")
    @Tag("BLOCKER")
    @DisplayName("TC_WEB_1: Проверка на непустой результат поиска книг")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("[data-testid='search__input']").
                setValue(searchQuery).pressEnter();
        $$("[data-testid='search__content--wrapper']")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "Толкин, Властелин колец",
            "Толстой, Детство. Отрочество. Юность"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} в категории Серии должна быть {1}")
    @Tag("BLOCKER")
    @DisplayName("TC_WEB_2: Проверка поиска определенной серии книг")
    void searchResultsShouldContainExpectedBookSeries(String searchQuery, String bookName) {
        $("[data-testid='search__input']").
                setValue(searchQuery).pressEnter();
        $("[data-testid='navigation__tabsList']")
                .$(byText("Серии"))
                .click();
        $("[data-testid='search__content--wrapper']")
                .shouldHave(text(bookName));
    }

    @CsvFileSource(resources = "/testdata/searchResultsShouldContainExpectedBooks")
    @ParameterizedTest(name = "Для поискового запроса {0} в категории Книги должна быть {1}")
    @Tag("BLOCKER")
    @DisplayName("TC_WEB_3: Проверка поиска определенной книги")
    void searchResultsShouldContainExpectedBooks(String searchQuery, String bookName) {
        $("[data-testid='search__input']").
                setValue(searchQuery).pressEnter();
        $("[data-testid='navigation__tabsList']")
                .shouldHave(text("Книги"));
        $("[data-testid='search__content--wrapper']")
                .shouldHave(text(bookName));
    }
}
