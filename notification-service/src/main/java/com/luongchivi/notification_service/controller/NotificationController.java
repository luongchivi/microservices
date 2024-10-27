package com.luongchivi.notification_service.controller;

import com.luongchivi.notification_service.dto.event.NotificationEvent;
import com.luongchivi.notification_service.dto.request.EmailRequest;
import com.luongchivi.notification_service.dto.request.Recipient;
import com.luongchivi.notification_service.dto.request.Sender;
import com.luongchivi.notification_service.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationController {

    EmailService emailService;

    @KafkaListener(topics = "notification-delivery", groupId = "notification-group", properties = {
            "spring.json.value.default.type=com.luongchivi.notification_service.dto.event.NotificationEvent"
    })
    public void listenNotificationDelivery(NotificationEvent message){
        log.info("Message received: {}", message);
        emailService.sendEmail(EmailRequest.builder()
                .sender(Sender.builder().name("FoorShop").email("chivi060399@gmail.com").build())
                .to(List.of(Recipient.builder().email(message.getRecipient()).build()))
                .subject(message.getSubject())
                .htmlContent(message.getBody())
                .build());
    }
}
