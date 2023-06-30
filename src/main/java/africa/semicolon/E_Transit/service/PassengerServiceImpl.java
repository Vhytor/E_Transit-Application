package africa.semicolon.E_Transit.service;

import africa.semicolon.E_Transit.AppUtilities;
import africa.semicolon.E_Transit.cloud.CloudService;
import africa.semicolon.E_Transit.config.distance.DistanceConfig;
import africa.semicolon.E_Transit.data.dtos.requests.BookRideRequest;
import africa.semicolon.E_Transit.data.dtos.requests.Location;
import africa.semicolon.E_Transit.data.dtos.requests.passengerRequest.RegisterPassengerRequest;
import africa.semicolon.E_Transit.data.dtos.response.ApiResponse;
import africa.semicolon.E_Transit.data.dtos.response.DistanceMatrixElement;
import africa.semicolon.E_Transit.data.dtos.response.GoogleDistanceResponse;
import africa.semicolon.E_Transit.data.dtos.response.passengerResponse.RegisterPassengerResponse;
import africa.semicolon.E_Transit.data.models.AppUser;
import africa.semicolon.E_Transit.data.models.Passenger;
import africa.semicolon.E_Transit.data.models.Role;
import africa.semicolon.E_Transit.data.repositories.PassengerRepository;
import africa.semicolon.E_Transit.exception.BusinessLogicException;
import africa.semicolon.E_Transit.mapper.ParaMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import static africa.semicolon.E_Transit.AppUtilities.NUMBER_OF_ITEMS_PER_PAGE;

@Service
@AllArgsConstructor
@Slf4j
public class PassengerServiceImpl implements PassengerService{
    private final PassengerRepository passengerRepository;

    private final CloudService cloudService;

    private final DistanceConfig directionConfig;

    private final PasswordEncoder passwordEncoder;


//    private ModelMapper mapper;
   // private ModelMapper modelMapper = new ModelMapper();


    @Override
    public RegisterPassengerResponse register(RegisterPassengerRequest registerRequest) {
        AppUser appUser = ParaMapper.map(registerRequest);
        appUser.setRoles(new HashSet<>());
        appUser.getRoles().add(Role.PASSENGER);
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        appUser.setCreatedAt(LocalDateTime.now().toString());
        appUser.setCreatedAt(LocalDateTime.now().toString());
        Passenger passenger = new Passenger();
        passenger.setUserDetails(appUser);
        Passenger savedPassenger = passengerRepository.save(passenger);
        RegisterPassengerResponse registerResponse = getRegisterResponse(savedPassenger);

        return registerResponse;
    }

    @Override
    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId).orElseThrow(()->
                new BusinessLogicException(
                        String.format("Passenger with id %d not found",passengerId)
                ));
    }

    @Override
    public Optional<Passenger> getPassengerBy(Long passengerId) {
        return passengerRepository.findById(passengerId);
    }

    @Override
    public Passenger updatePassenger(Long passengerId, JsonPatch updatePayLoad) {
        ObjectMapper mapper = new ObjectMapper();
       Passenger foundPassenger =  getPassengerById(passengerId);

      JsonNode node =  mapper.convertValue(foundPassenger, JsonNode.class);

      try{
        JsonNode updateNode =   updatePayLoad.apply(node);
          var updatedPassenger =mapper.convertValue(updateNode, Passenger.class);
          updatedPassenger = passengerRepository.save(updatedPassenger);
          return updatedPassenger;

      } catch ( JsonPatchException e) {
          log.error(e.getMessage());
          throw new RuntimeException();
      }

    }

    @Override
    public void savePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public Page<Passenger> getAllPassenger(int pageNumber) {
        if(pageNumber < 1) pageNumber = 0;
        else pageNumber = pageNumber - 1;
        Pageable pageable = PageRequest.of(pageNumber,NUMBER_OF_ITEMS_PER_PAGE);
        return passengerRepository.findAll(pageable);

    }

    @Override
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public ApiResponse bookRide(BookRideRequest bookRideRequest) {
        Passenger foundPassenger = getPassengerById(bookRideRequest.getPassengerId());
        
        if(foundPassenger == null) throw new BusinessLogicException(
                String.format("passenger with id %d not found",bookRideRequest.getPassengerId())
                
        );

        DistanceMatrixElement distanceInformation = getDistanceInformation(bookRideRequest.getOrigin(),bookRideRequest.getDestination());

        String eta = distanceInformation.getDuration().getText();

        BigDecimal fare = AppUtilities.calculateRideFare(distanceInformation.getDistance().getText());
        return ApiResponse.builder().fare(fare).estimatedTimeOfArrival(eta).build();

    }

    private DistanceMatrixElement getDistanceInformation(Location origin, Location destination) {
        RestTemplate restTemplate = new RestTemplate();
        String url = buildDistanceRequestUrl(origin,destination);
        ResponseEntity<GoogleDistanceResponse>response =
                restTemplate.getForEntity(url, GoogleDistanceResponse.class);
        return Objects.requireNonNull(response.getBody()).getRows().stream()
                .findFirst().orElseThrow()
                .getElements().stream()
                .findFirst()
                .orElseThrow();
    }

    private String buildDistanceRequestUrl(Location origin, Location destination) {
        return directionConfig.getGoogleDistanceUrl() + "/"+ AppUtilities.JSON_CONTENT+"?"
        +"destinations="+AppUtilities.buildLocation(destination)+"&origins="
                +AppUtilities.buildLocation(origin)+"&mode=driving"+"&traffic_model=pessimistic"
                +"&departure_time="+ LocalDateTime.now().toEpochSecond(ZoneOffset.of("+01:00"))
                +"&key="+directionConfig.getGoogleApiKey();
    }

    private static RegisterPassengerResponse getRegisterResponse(Passenger savedPassenger) {
        RegisterPassengerResponse registerResponse = new RegisterPassengerResponse();
        registerResponse.setCode(HttpStatus.CREATED.value());
       registerResponse.setId(savedPassenger.getId());
        registerResponse.setSuccessful(true);
        registerResponse.setMessage("User registration successful");
        return registerResponse;
    }
}
