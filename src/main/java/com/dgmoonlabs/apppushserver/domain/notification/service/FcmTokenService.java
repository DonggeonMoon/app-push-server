package com.dgmoonlabs.apppushserver.domain.notification.service;

import com.dgmoonlabs.apppushserver.domain.notification.dto.FcmTokenRequest;
import com.dgmoonlabs.apppushserver.domain.notification.repository.FcmTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FcmTokenService {
    private final FcmTokenRepository fcmTokenRepository;

    @Transactional
    public Long save(final FcmTokenRequest request) {
        return fcmTokenRepository.save(
                request.toEntity()
        ).getId();
    }
}
