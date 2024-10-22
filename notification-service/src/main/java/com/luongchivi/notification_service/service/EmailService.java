package com.luongchivi.notification_service.service;

import com.luongchivi.notification_service.dto.request.EmailRequest;
import com.luongchivi.notification_service.dto.response.EmailResponse;

public interface EmailService {
    EmailResponse sendEmail(EmailRequest request);
}
