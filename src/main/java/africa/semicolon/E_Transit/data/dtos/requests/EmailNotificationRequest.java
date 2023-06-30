package africa.semicolon.E_Transit.data.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationRequest {
    private final Sender sender = new Sender("e_transit","noreply@etransit.net");
    private List<Recipient> to = new ArrayList<>();
    private final String subject = "Welcome to e_transit";
    private  String htmlContent;
}
