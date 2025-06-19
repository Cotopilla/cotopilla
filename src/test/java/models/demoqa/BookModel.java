package models.demoqa;

import lombok.Data;

@Data
public class BookModel {
    private String isbn, title, subTitle, author,
    publish_date, publisher, pages, description, website;
}
