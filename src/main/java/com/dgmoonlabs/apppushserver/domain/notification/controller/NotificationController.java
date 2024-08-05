package com.dgmoonlabs.apppushserver.domain.notification.controller;

import com.dgmoonlabs.apppushserver.domain.notification.model.NotificationRequest;
import com.dgmoonlabs.apppushserver.domain.notification.service.NotificationService;
import com.dgmoonlabs.apppushserver.global.util.FirebaseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    @Value("${SERVICE_ACCOUNT_PRIVATE_KEY_FILE_PATH}")
    private String filePath;
    private final NotificationService notificationService;

    @PostConstruct
    public void init() {
        FirebaseUtils.initializeAppWithPrivateKeyFile(filePath);
    }

    @PostMapping("/send")
    public ResponseEntity<Void> send(final @RequestBody NotificationRequest request) {
        notificationService.send(request);
        return ResponseEntity.noContent().build();
    }
}
