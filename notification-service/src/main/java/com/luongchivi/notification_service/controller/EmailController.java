package com.luongchivi.notification_service.controller;

import com.luongchivi.notification_service.dto.request.EmailRequest;
import com.luongchivi.notification_service.dto.response.EmailResponse;
import com.luongchivi.notification_service.service.EmailService;
import com.luongchivi.notification_service.share.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailController {
    EmailService emailService;

    @PostMapping("/send")
    ApiResponse<EmailResponse> sendEmail(@RequestBody EmailRequest request) {
        EmailResponse emailResponse = emailService.sendEmail(request);
        return ApiResponse.<EmailResponse>builder().results(emailResponse).build();
    }

}
