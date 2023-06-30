package africa.semicolon.E_Transit.service;

import africa.semicolon.E_Transit.AppUtilities;
import africa.semicolon.E_Transit.data.dtos.requests.EmailNotificationRequest;
import africa.semicolon.E_Transit.data.dtos.requests.Recipient;
import africa.semicolon.E_Transit.data.dtos.requests.adminRequest.InviteAdminRequest;
import africa.semicolon.E_Transit.data.dtos.response.ApiResponse;
import africa.semicolon.E_Transit.data.models.Admin;
import africa.semicolon.E_Transit.data.models.AppUser;
import africa.semicolon.E_Transit.data.repositories.AdminRepository;
import africa.semicolon.E_Transit.exception.BusinessLogicException;
import africa.semicolon.E_Transit.notification.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    private final MailService mailService;
    @Override
    public ApiResponse sendInviteRequests(Set<InviteAdminRequest> inviteAdminRequestList) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        var recipients = inviteAdminRequestList.stream()
                .map(inviteAdminRequest -> createAdminProfile(inviteAdminRequest))
                .map(inviteAdminRequest -> new Recipient(inviteAdminRequest.getUserDetails().getName(), inviteAdminRequest.getUserDetails().getEmail()))
                .toList();
        request.getTo().addAll(recipients);

        String adminMail = AppUtilities.getAdminMailTemplate();
        request.setHtmlContent(String.format(adminMail, "admin", AppUtilities.generateVerificationLink(0L)));
        var response = mailService.sendHtmlMail(request);
        if (response!=null) return ApiResponse.builder().message("invite requests sent").build();
        throw new BusinessLogicException("invite requests sending failed");
    }


    private Admin createAdminProfile(InviteAdminRequest inviteAdminRequest) {
        Admin admin = new Admin();
        admin.setUserDetails(new AppUser());
        admin.getUserDetails().setName(inviteAdminRequest.getName());
        admin.getUserDetails().setEmail(inviteAdminRequest.getEmail());
        adminRepository.save(admin);
        return admin;
    }
    }
