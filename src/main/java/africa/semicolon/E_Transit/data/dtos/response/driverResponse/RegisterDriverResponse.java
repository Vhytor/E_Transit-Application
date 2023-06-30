package africa.semicolon.E_Transit.data.dtos.response.driverResponse;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDriverResponse {

    private String message;
    private int code;
    private boolean isSuccessful;
    private Long id;
}
