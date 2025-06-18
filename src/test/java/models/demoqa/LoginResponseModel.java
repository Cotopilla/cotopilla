package models.demoqa;

import lombok.Data;

@Data
public class LoginResponseModel {
    private String userId, username, password, token, expires, created_date, isActive;

}
