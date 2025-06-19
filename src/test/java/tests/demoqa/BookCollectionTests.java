package tests.demoqa;

import io.qameta.allure.Owner;
import models.demoqa.LoginResponseModel;
import org.junit.jupiter.api.*;

@Tag("bookstore")
@Owner("Evgeniya Malysheva")
public class BookCollectionTests extends TestBase {

    protected BookStoreSteps newBookStoreSession = new BookStoreSteps();
    protected LoginResponseModel authResponse = newBookStoreSession.demoqaAuth();
    protected String usedId = authResponse.getUserId(),
            token = authResponse.getToken(),
            expires = authResponse.getExpires();

    protected String firstBookId = "9781449365035",
            firstBookName = "Speaking JavaScript",
            secondBookId = "9781491904244",
            secondBookName = "You Don't Know JS";

    @Test
    void addBookToCollectionTest() {
        newBookStoreSession.deleteAllBooksFromProfileCollection(usedId, token)
                .addBooksToProfileCollection(firstBookId, usedId, token)
                .setDemoqaCookie(usedId, token, expires)
                .checkBookNameInProfileCollection(firstBookName);
    }

    @Test
    void addTwoBooksToCollection_thenDeleteOneBook_Test() {
        newBookStoreSession.deleteAllBooksFromProfileCollection(usedId, token)
                .addBooksToProfileCollection(firstBookId, secondBookId, usedId, token)
                .deleteOneBookFromProfileCollection(firstBookId, usedId, token)
                .setDemoqaCookie(usedId, token, expires)
                .checkBookNameInProfileCollection(secondBookName);
    }
}
