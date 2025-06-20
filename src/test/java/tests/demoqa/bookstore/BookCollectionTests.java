package tests.demoqa.bookstore;

import io.qameta.allure.Owner;
import models.demoqa.LoginResponseModel;
import org.junit.jupiter.api.*;

@Tag("bookstore")
@Owner("Evgeniya Malysheva")
public class BookCollectionTests extends TestBase {

    private final String firstBookId = "9781449365035",
            firstBookName = "Speaking JavaScript",
            secondBookId = "9781491904244",
            secondBookName = "You Don't Know JS";
    private String userId,
            token,
            expires;

    @BeforeEach
    public void authentificateAndSetParams() {
        LoginResponseModel authResponse = newBookStoreSession.demoqaAuth();
        userId = authResponse.getUserId();
        token = authResponse.getToken();
        expires = authResponse.getExpires();
    }

    @Test
    @DisplayName("Добавляем в коллекцию профиля одну книгу")
    void addBookToCollectionTest() {
        newBookStoreSession.deleteAllBooksFromProfileCollection(userId, token)
                .addBooksToProfileCollection(firstBookId, userId, token)
                .setDemoqaCookie(userId, token, expires)
                .checkBookNameInProfileCollection(firstBookName);
    }

    @Test
    @DisplayName("Добавляем в коллекцию профиля две книги, одну удаляем")
    void addTwoBooksToCollection_thenDeleteOneBook_Test() {
        newBookStoreSession.deleteAllBooksFromProfileCollection(userId, token)
                .addBooksToProfileCollection(firstBookId, secondBookId, userId, token)
                .deleteOneBookFromProfileCollection(firstBookId, userId, token)
                .setDemoqaCookie(userId, token, expires)
                .checkBookNameInProfileCollection(secondBookName);
    }
}
