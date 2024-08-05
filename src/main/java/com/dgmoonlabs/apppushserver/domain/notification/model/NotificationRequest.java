package com.dgmoonlabs.apppushserver.domain.notification.model;

import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class NotificationRequest {
    private String topicName;
    private String title;
    private String body;
    private String imageUrl;
}
