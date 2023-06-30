package africa.semicolon.E_Transit.data.repositories;

import africa.semicolon.E_Transit.data.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
