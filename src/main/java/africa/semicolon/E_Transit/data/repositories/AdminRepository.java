package africa.semicolon.E_Transit.data.repositories;

import africa.semicolon.E_Transit.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
