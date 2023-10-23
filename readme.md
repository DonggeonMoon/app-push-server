# app-push-server

오늘의 증시 일정을 알려주는 앱 푸시 알림 서버입니다.

## 기술 스택

<img src="https://img.shields.io/badge/Java-007396.svg?&style=flat&logo=Java&logoColor=white" alt="Java"> <img src="https://img.shields.io/badge/Spring_Boot-6DB33F.svg?&style=flat&logo=SpringBoot&logoColor=white" alt="Spring Boot">

* Java 17
* Spring Boot 2.7

# DB document 구조

```mermaid
erDiagram
    
    schedule ||--|| baseEntity : ""
    schedule {
        string id PK "not null"
        string dateTime "not null"
        string iteration "not null"
        string startDate "not null"
        string endDate "not null"
        string title "not null"
        string description "not null"
    }
    
    baseEntity {
        string createdAt "not null"
        string modifiedAt "not null"
        string deletedAt "null"
    }
```

```mongodb-json
{
    "id" : "1",
    "dateTime" : "2023-11-01T00:00:00Z",
    "iteration" : "ONCE",
    "startDate" : "2023-10-23",
    "endDate" : "2099-12-23",
    "title": "FOMC 정례 회의",
    "description" : "FOMC 정례 회의(11/01 ~ 11/02, 현지 시간 10/31 ~ 11/01)",
    "androidConfigs" : {
    },
    "apnConfigs" : {
    },
    "webPushConfigs" : {
    },
    "createdAt" : "2023-10-23T00:00:00Z",
    "modifiedAt" : "2023-10-23T00:00:00Z",
    "deleted_At" : null
}
```
