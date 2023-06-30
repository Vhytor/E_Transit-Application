package africa.semicolon.E_Transit.data.dtos.response;

import africa.semicolon.E_Transit.data.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminResponse {
    private Long employeeId;
    private AppUser userDetails;
}
