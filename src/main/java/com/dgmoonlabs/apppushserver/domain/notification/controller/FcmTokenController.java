package com.dgmoonlabs.apppushserver.domain.notification.controller;

import com.dgmoonlabs.apppushserver.domain.notification.dto.FcmTokenRequest;
import com.dgmoonlabs.apppushserver.domain.notification.service.FcmTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fcmToken")
public class FcmTokenController {
    private final FcmTokenService fcmTokenService;

    @PostMapping
    public ResponseEntity<String> createFcmToken(final @RequestBody FcmTokenRequest request) {
        return ResponseEntity.created(
                URI.create("/fcmToken/" + fcmTokenService.save(request))
        ).build();
    }
}
