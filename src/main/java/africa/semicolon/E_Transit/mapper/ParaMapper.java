package africa.semicolon.E_Transit.mapper;

import africa.semicolon.E_Transit.data.dtos.requests.passengerRequest.RegisterPassengerRequest;
import africa.semicolon.E_Transit.data.models.AppUser;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParaMapper {

    public static AppUser map(RegisterPassengerRequest request){
        AppUser appUser = new AppUser();
        appUser.setName(request.getName());
        appUser.setPassword(request.getPassword());
        appUser.setEmail(request.getEmail());

        return appUser;
    }

//    public static AppUser map(RegisterDriverRequest request){
//        AppUser appUser = new AppUser();
//        appUser.setName(request.getName());
//        appUser.setPassword(request.getPassword());
//        appUser.setEmail(request.getEmail());
//
//        return appUser;
//    }
}
