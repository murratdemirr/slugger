package com.demir.core.email.entity;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.demir.core.email.entity.Email.*;

@Entity
@Table(name = "EMAILS", schema = "PUBLIC")
@NamedQueries({
        @NamedQuery(name = FIND_ALL, query = "SELECT e FROM Email e"),
        @NamedQuery(name = COUNT_BY_EMAIL, query = "SELECT SUM(e.encounteredTimes) FROM Email e WHERE e.email=:email")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = FIND_EMAIL_INFOS, query = "SELECT t.email AS email_address, SUM(t.encountered_times) AS total_count FROM public.emails t GROUP BY t.email", resultSetMapping = EMAIL_INFO_MAPPING)
})
@SqlResultSetMapping(
        name = EMAIL_INFO_MAPPING,
        classes = {
                @ConstructorResult(
                        targetClass = EmailInfo.class,
                        columns = {
                                @ColumnResult(name = "email_address"),
                                @ColumnResult(name = "total_count", type = Long.class)
                        }
                )
        }
)
public class Email {

    private static final String PREFIX = "EmailEntity.";
    public static final String FIND_ALL = PREFIX + "findAll";
    public static final String FIND_EMAIL_INFOS = PREFIX + "findEmailInfos";
    public static final String COUNT_BY_EMAIL = PREFIX + "countByEmail";
    public static final String EMAIL_INFO_MAPPING = "EmailInfoMapping";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "CREATED_TIME", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "ENCOUNTERED_TIMES", nullable = false)
    private Long encounteredTimes;

    @Column(name = "BATCH_ID", nullable = false)
    private String batchId;

    public Email() {
    }

    @PrePersist
    public void persist() {
        this.createdTime = LocalDateTime.now();
    }

    public Email(String email) {
        this.email = email;
        this.encounteredTimes = Long.valueOf(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Long getEncounteredTimes() {
        return encounteredTimes;
    }

    public void setEncounteredTimes(Long encounteredTimes) {
        this.encounteredTimes = encounteredTimes;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
}
