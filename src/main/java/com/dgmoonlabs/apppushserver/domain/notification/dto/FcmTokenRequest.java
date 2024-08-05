package com.dgmoonlabs.apppushserver.domain.notification.dto;

import com.dgmoonlabs.apppushserver.domain.notification.model.FcmToken;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class FcmTokenRequest {
    private String token;

    public FcmToken toEntity() {
        return FcmToken.builder()
                .token(token)
                .build();
    }
}
