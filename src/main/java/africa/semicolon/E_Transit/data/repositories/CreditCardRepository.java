package africa.semicolon.E_Transit.data.repositories;

import africa.semicolon.E_Transit.data.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {
}
