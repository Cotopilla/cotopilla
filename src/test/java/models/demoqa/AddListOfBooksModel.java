package models.demoqa;

import lombok.Data;

@Data
public class AddListOfBooksModel {
    private String userId;
    private IsbnModel[] collectionOfIsbns;
}
