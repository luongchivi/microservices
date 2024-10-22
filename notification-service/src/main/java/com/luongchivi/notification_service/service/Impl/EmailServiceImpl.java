package com.luongchivi.notification_service.service.Impl;

import com.luongchivi.notification_service.dto.request.EmailRequest;
import com.luongchivi.notification_service.dto.response.EmailResponse;
import com.luongchivi.notification_service.exception.AppException;
import com.luongchivi.notification_service.exception.ErrorCode;
import com.luongchivi.notification_service.repository.httpclient.EmailClient;
import com.luongchivi.notification_service.service.EmailService;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {
    EmailClient emailClient;

    @NonFinal
    @Value("${brevo.apiKey}")
    String apiKey;

    public EmailResponse sendEmail(EmailRequest request) {
        try {
            return emailClient.sendEmail(apiKey, request);
        } catch (FeignException e) {
            throw new AppException(ErrorCode.ERROR_SEND_EMAIL);
        }
    }
}
