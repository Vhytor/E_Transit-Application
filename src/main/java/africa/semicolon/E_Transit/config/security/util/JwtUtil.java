package africa.semicolon.E_Transit.config.security.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtUtil {
    private final String jwtSecret;
}
