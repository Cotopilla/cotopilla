package models.demoqa;

import lombok.Data;

@Data
public class AddBookResponseModel {
    private String userId, username;
    private BookModel[] books;
}
