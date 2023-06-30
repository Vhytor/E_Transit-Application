package africa.semicolon.E_Transit.data.repositories;

import africa.semicolon.E_Transit.data.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PassengerRepository extends JpaRepository<Passenger,Long> {
}
