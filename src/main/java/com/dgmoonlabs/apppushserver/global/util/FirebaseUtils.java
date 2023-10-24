package com.dgmoonlabs.apppushserver.global.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.NONE)
@Slf4j
public class FirebaseUtils {
    public static void initializeAppWithPrivateKeyFile(String filePath) {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new FileInputStream(filePath));
            FirebaseApp.initializeApp(
                    FirebaseOptions.builder()
                            .setCredentials(googleCredentials)
                            .build()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMessage(Message message) {
        try {
            log.info("Successfully sent message: {} ", FirebaseMessaging.getInstance().send(message));
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
