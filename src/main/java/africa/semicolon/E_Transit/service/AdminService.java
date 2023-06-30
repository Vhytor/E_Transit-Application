package africa.semicolon.E_Transit.service;

import africa.semicolon.E_Transit.data.dtos.requests.adminRequest.InviteAdminRequest;
import africa.semicolon.E_Transit.data.dtos.response.ApiResponse;

import java.util.Set;

public interface AdminService {
    ApiResponse sendInviteRequests(Set<InviteAdminRequest> inviteAdminRequestList);
}
