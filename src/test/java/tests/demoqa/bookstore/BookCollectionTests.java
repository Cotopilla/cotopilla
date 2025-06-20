package tests.demoqa.bookstore;

import io.qameta.allure.Owner;
import models.demoqa.IsbnModel;
import models.demoqa.LoginResponseModel;
import org.junit.jupiter.api.*;

import java.util.List;

@Tag("bookstore")
@Owner("Evgeniya Malysheva")
public class BookCollectionTests extends TestBase {

    private final String firstBookId = "9781449365035",
            firstBookName = "Speaking JavaScript",
            secondBookId = "9781491904244",
            secondBookName = "You Don't Know JS";
    private final List<IsbnModel> listOfOneBook = List.of
            (new IsbnModel(firstBookId));
    private final List<IsbnModel> listOfTwoBooks = List.of
            (new IsbnModel(firstBookId), new IsbnModel(secondBookId));
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
                .addBooksToProfileCollection(listOfOneBook, userId, token)
                .setDemoqaCookie(userId, token, expires)
                .checkOneBookNameAndQuantityInProfileCollection(firstBookName);
    }

    @Test
    @DisplayName("Добавляем в коллекцию профиля две книги, одну удаляем")
    void addTwoBooksToCollection_thenDeleteOneBook_Test() {
        newBookStoreSession.deleteAllBooksFromProfileCollection(userId, token)
                .addBooksToProfileCollection(listOfTwoBooks, userId, token)
                .deleteOneBookFromProfileCollection(firstBookId, userId, token)
                .setDemoqaCookie(userId, token, expires)
                .checkOneBookNameAndQuantityInProfileCollection(secondBookName);
    }
}
