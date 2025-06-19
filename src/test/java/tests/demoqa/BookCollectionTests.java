package tests.demoqa;

import io.qameta.allure.Owner;
import models.demoqa.LoginResponseModel;
import org.junit.jupiter.api.*;

@Tag("bookstore")
@Owner("Evgeniya Malysheva")
public class BookCollectionTests extends TestBase {
    protected BookStoreSteps newBookUsage = new BookStoreSteps();
    protected LoginResponseModel authResponse = newBookUsage.demoqaAuth();
    protected String usedId = authResponse.getUserId(),
            token = authResponse.getToken(),
            firstBookId = "9781449365035",
            firstBookName = "Speaking JavaScript",
            secondBookId = "9781491904244",
            secondBookName = "You Don't Know JS";

    @Test
    void addBookToCollectionTest() {
        newBookUsage.deleteAllBooksFromProfileCollection(usedId, token)
                .addBookToProfileCollection(firstBookId, usedId, token)
                .setDemoqaCookie(authResponse)
                .checkBookNameInProfileCollection(firstBookName);
    }

    @Test
    void addTwoBooksToCollection_thenDeleteOneBook_Test() {
        newBookUsage.setDemoqaCookie(authResponse)
                .addSomeBooksToProfileCollection(firstBookId, secondBookId, usedId, token)
                .deleteOneBookFromProfileCollection(firstBookId, usedId, token)
                .checkBookNameInProfileCollection(secondBookName);
    }
}
