package africa.semicolon.E_Transit.Controller;

import africa.semicolon.E_Transit.data.dtos.requests.driverRequest.RegisterDriverRequest;
import africa.semicolon.E_Transit.data.dtos.response.ApiResponse;
import africa.semicolon.E_Transit.data.dtos.response.driverResponse.RegisterDriverResponse;
import africa.semicolon.E_Transit.exception.BusinessLogicException;
import africa.semicolon.E_Transit.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/driver")
@AllArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping(value = "/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?>register(@ModelAttribute RegisterDriverRequest request){
        try {
            RegisterDriverResponse response = driverService.register(request);
            return ResponseEntity.ok(response);
        } catch (BusinessLogicException exception) {
            return ResponseEntity.badRequest()
                            .body(ApiResponse.builder()
                            .message(exception.getMessage())
                            .build());
        }
    }
}


