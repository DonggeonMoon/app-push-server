package com.dgmoonlabs.apppushserver.domain.calendar.model;

import com.dgmoonlabs.apppushserver.domain.calendar.enums.Iteration;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.WebpushConfig;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "schedule")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode(callSuper = false, exclude = {"dateTime"})
public class Schedule extends BaseEntity implements Serializable {
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
