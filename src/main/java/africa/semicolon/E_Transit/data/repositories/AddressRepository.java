package africa.semicolon.E_Transit.data.repositories;

import africa.semicolon.E_Transit.data.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
