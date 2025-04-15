package github;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GithubSelenideTest {
    @Test
    void shouldFindSelenideRepositoryAtTheTop() {

        // открыть главную страницу
        open("https://github.com/");
        // ввести в поле поиска selenide и нажать enter
        $("[placeholder='Search GitHub']").setValue("selenide").pressEnter();
        // кликнуть на первый репозиторий из списка найденых
        $$("ul.repo-list li").first().$("a").click();
        // проверка: заголовок selenide/selenide
        $("#repository-container-header").shouldHave(text("selenide / selenide"));

    }

    @Test
    void andreiSolntsevShouldBeTheFirstContributor() {
        // открыть страницу репозитория селенида
        open("https://github.com/selenide/selenide");
        // подвести мышку к первому аватару из блока contributors
        $("div.Layout-sidebar").$(byText("Contributors"))
                //.closest(".BorderGrid-cell").$$("ul li").first().hover();
                .closest("h2").sibling(0).$$("li").first().hover();
        // проверка: во всплывающем окне есть текст Andrei Solntsev
        $(".Popover-message").shouldHave(text("Andrei Solntsev"));
    }

    @Test
    void findJUnit5CodeForSoftAssertionsInWiki() {
        //Откройте страницу Selenide в Github
        open("https://github.com/selenide/selenide");

        //Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();
        //Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $$("#wiki-body a.internal")
                .shouldHave(itemWithText("Soft assertions"));

        //Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $("#wiki-body a[href$='SoftAssertions']").click();
        $$(".markdown-heading")
                .findBy(text("Using JUnit5"))
                .sibling(0)
                .$("pre")
                .shouldHave(text(
                                "@ExtendWith({SoftAssertsExtension.class})\n" +
                                "        class Tests {\n" +
                                "            @Test\n" +
                                "            void test() {\n" +
                                "                Configuration.assertionMode = SOFT;\n" +
                                "                open(\"page.html\");\n" +
                                "\n" +
                                "                $(\"#first\").should(visible).click();\n" +
                                "                $(\"#second\").should(visible).click();\n" +
                                "            }\n" +
                                "        }"));

    }
}
