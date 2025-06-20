package api.authorization;

import lombok.Data;

@Data
public class AuthorizationResponseDto {
    private String userId, username, password, token, expires, created_date, isActive;
}
