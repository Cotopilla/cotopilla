package tests.demoqa.bookstore;

import helpers.withlogin.WithLogin;
import io.qameta.allure.Owner;
import models.demoqa.IsbnModel;
import models.demoqa.LoginResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

@Tag("bookstore")
@Owner("Evgeniya Malysheva")
public class BookCollectionWithLoginTests extends TestBase {
    private final String firstBookId = "9781449365035",
            firstBookName = "Speaking JavaScript",
            secondBookId = "9781491904244",
            secondBookName = "You Don't Know JS";
    private final List<IsbnModel> listOfOneBook = List.of
            (new IsbnModel(firstBookId));
    private final List<IsbnModel> listOfTwoBooks = List.of
            (new IsbnModel(firstBookId), new IsbnModel(secondBookId));
    private String userId,
            token;

    @BeforeEach
    public void authentificateAndSetParams() {
        LoginResponseModel authResponse = newBookStoreSession.demoqaAuth();
        userId = authResponse.getUserId();
        token = authResponse.getToken();
    }

    @Test
    @WithLogin
    @DisplayName("Добавляем в коллекцию профиля одну книгу")
    void addBookToCollectionTest() {
        newBookStoreSession.deleteAllBooksFromProfileCollection(userId, token)
                .addBooksToProfileCollection(listOfOneBook, userId, token)
                .checkOneBookNameAndQuantityInProfileCollection(firstBookName);
    }

    @Test
    @WithLogin
    @DisplayName("Добавляем в коллекцию профиля две книги, одну удаляем")
    void addTwoBooksToCollection_thenDeleteOneBook_Test() {
        newBookStoreSession.deleteAllBooksFromProfileCollection(userId, token)
                .addBooksToProfileCollection(listOfTwoBooks, userId, token)
                .deleteOneBookFromProfileCollection(firstBookId, userId, token)
                .checkOneBookNameAndQuantityInProfileCollection(secondBookName);
    }
}
