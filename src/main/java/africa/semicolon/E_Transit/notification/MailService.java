package africa.semicolon.E_Transit.notification;

import africa.semicolon.E_Transit.data.dtos.requests.EmailNotificationRequest;

public interface MailService {
    String sendHtmlMail(EmailNotificationRequest request);
}
