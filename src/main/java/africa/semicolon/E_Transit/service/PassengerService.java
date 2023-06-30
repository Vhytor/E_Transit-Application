package africa.semicolon.E_Transit.service;

import africa.semicolon.E_Transit.data.dtos.requests.BookRideRequest;
import africa.semicolon.E_Transit.data.dtos.requests.passengerRequest.RegisterPassengerRequest;
import africa.semicolon.E_Transit.data.dtos.response.ApiResponse;
import africa.semicolon.E_Transit.data.dtos.response.passengerResponse.RegisterPassengerResponse;
import africa.semicolon.E_Transit.data.models.Passenger;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PassengerService {

    RegisterPassengerResponse register(RegisterPassengerRequest registerRequest);

    Passenger getPassengerById(Long userId);

    Optional<Passenger> getPassengerBy(Long passengerId);

    Passenger updatePassenger(Long passengerId, JsonPatch updatePayLoad);

    void savePassenger(Passenger passenger);

    Page<Passenger> getAllPassenger(int pageNumber);

    void deletePassenger(Long id);

    ApiResponse bookRide(BookRideRequest bookRideRequest);


}
