package quackrbackend.security.jwt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTLoginData {

    private String username;
    private String password;
}
