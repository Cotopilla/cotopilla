package models.lombok.reqresin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAndUpdateBodyModel {
    private String name, job;
}
