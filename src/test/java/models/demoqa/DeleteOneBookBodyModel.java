package models.demoqa;

import lombok.Data;

@Data
public class DeleteOneBookBodyModel {
    private String userId, isbn;
}
