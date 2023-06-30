package africa.semicolon.E_Transit.data.repositories;

import africa.semicolon.E_Transit.data.models.BankInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankInformationRepository extends JpaRepository<BankInformation,Long> {
}
