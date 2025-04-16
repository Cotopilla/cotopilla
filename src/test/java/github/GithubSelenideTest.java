package github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.HoverOptions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class GithubSelenideTest {
    @Test
    void shouldFindSelenideRepositoryAtTheTopTest() {

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
    void andreiSolntsevShouldBeTheFirstContributorTest() {
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
    void findJUnit5CodeForSoftAssertionsInWikiTest() {
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

    @Test
    void hoverTest() {
        //На главной странице GitHub выберите: Меню -> Solutions -> Enterprize
        // (с помощью команды hover для Solutions)
        // Убедитесь, что загрузилась нужная страница
        // (например, что заголовок: "The AI-powered developer platform.").

        open("https://github.com/");
        $(".HeaderMenu-nav").$(byText("Solutions")).hover();
        $("a[href='https://github.com/enterprise']").click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered\ndeveloper platform"));
    }

    @Test
    void dragAndDropTest() {
        //Запрограммируйте Drag&Drop с помощью Selenide.actions()
        // - Откройте https://the-internet.herokuapp.com/drag_and_drop
        // - Перенесите прямоугольник А на место В
        // - Проверьте, что прямоугольники действительно поменялись
        // - В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест, если использовать её вместо actions()

        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a header").shouldHave(text("A"));
        actions().moveToElement($("#column-a"))
                .clickAndHold()
                .moveToElement($("#column-b"))
                .release()
                .perform();
        $("#column-a header").shouldHave(text("B"));
        $("#column-b").dragAndDrop(to("#column-a"));
        $("#column-a header").shouldHave(text("A"));
    }
}
