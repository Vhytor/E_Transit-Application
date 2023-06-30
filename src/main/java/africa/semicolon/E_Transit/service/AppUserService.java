package africa.semicolon.E_Transit.service;

import africa.semicolon.E_Transit.data.dtos.response.ApiResponse;
import africa.semicolon.E_Transit.data.models.AppUser;
import org.springframework.web.multipart.MultipartFile;

public interface AppUserService {

    ApiResponse uploadProfileImage(MultipartFile profileImage, Long userId);

    ApiResponse verifyAccount(Long userId,String token);

    AppUser getByEmail(String email);
}
