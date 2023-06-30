package africa.semicolon.E_Transit.config.security.services;


import africa.semicolon.E_Transit.config.security.users.SecureUser;
import africa.semicolon.E_Transit.data.models.AppUser;
import africa.semicolon.E_Transit.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserService.getByEmail(username);
        return new SecureUser(user);
    }
}