package com.dgmoonlabs.apppushserver.domain.schedule.model;

import com.dgmoonlabs.apppushserver.domain.schedule.enums.Iteration;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.WebpushConfig;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Schedule extends BaseEntity {
    @Id
    private String id;

    private LocalDateTime dateTime;

    private Iteration iteration;

    private LocalDate startDate;

    private LocalDate endDate;

    private String title;

    private String description;

    private AndroidConfig androidConfig;

    private ApnsConfig apnsConfig;

    private WebpushConfig webpushConfig;
}
