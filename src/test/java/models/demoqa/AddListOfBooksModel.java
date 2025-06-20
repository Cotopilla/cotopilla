package models.demoqa;

import lombok.Data;

import java.util.List;

@Data
public class AddListOfBooksModel {
    private String userId;
    private List<IsbnModel> collectionOfIsbns;
}
