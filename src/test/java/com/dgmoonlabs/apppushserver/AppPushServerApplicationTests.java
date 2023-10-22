package com.dgmoonlabs.apppushserver;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
@Slf4j
class AppPushServerApplicationTests {
    private String topic = "STOCK_CALENDAR_ALL_USERS";

    @Value("${firebase.service-account-file-path}")
    private String serviceAccountFilePath;

    @Test
    void test() throws FirebaseMessagingException, IOException {
        FileInputStream fileInputStream = new FileInputStream(serviceAccountFilePath);

        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(fileInputStream))
                .build();
        FirebaseApp.initializeApp(firebaseOptions);

        Message message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setNotification(Notification.builder()
                        .setTitle("Test Notification")
                        .setBody("This is a test!")
                        .build())
                .setTopic(topic)
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        log.info("Successfully sent message: " + response);
    }
}
