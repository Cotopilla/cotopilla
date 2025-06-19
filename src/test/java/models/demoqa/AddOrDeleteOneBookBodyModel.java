package models.demoqa;

import lombok.Data;

@Data
public class AddOrDeleteOneBookBodyModel {
    private String userId, isbn;
}
