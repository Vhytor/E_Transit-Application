package africa.semicolon.E_Transit.data.repositories;

import africa.semicolon.E_Transit.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findByEmail(String email);
}
