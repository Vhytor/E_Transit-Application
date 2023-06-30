package africa.semicolon.E_Transit.service;

import africa.semicolon.E_Transit.data.dtos.requests.driverRequest.RegisterDriverRequest;
import africa.semicolon.E_Transit.data.dtos.response.driverResponse.RegisterDriverResponse;
import africa.semicolon.E_Transit.data.models.Driver;

import java.util.Optional;

public interface DriverService {

     RegisterDriverResponse register(RegisterDriverRequest registerDriverRequest);

     Optional<Driver> getDriverBy(Long driverId);

     void saveDriver(Driver driver);

//     Driver getDriverById(Long id);
//
//     Driver updateDriver(Long driverId,JsonPatch updatePayLoad);
//
//     void deleteDriver(Long id );
}
