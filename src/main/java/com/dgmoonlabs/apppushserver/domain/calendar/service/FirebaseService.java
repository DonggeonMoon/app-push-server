package com.dgmoonlabs.apppushserver.domain.calendar.service;

import com.dgmoonlabs.apppushserver.global.util.FirebaseUtils;
import com.google.firebase.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FirebaseService {
    @Value("${firebase.service-account-file-path}")
    private String serviceAccountFilePath;

    public void sendMessage(Message message) {
        FirebaseUtils.initializeAppWithPrivateKeyFile(serviceAccountFilePath);
        FirebaseUtils.sendMessage(message);
    }
}
