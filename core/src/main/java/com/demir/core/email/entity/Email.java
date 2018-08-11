package com.demir.core.email.entity;

import javax.persistence.*;

import static com.demir.core.email.entity.Email.EMAIL_INFO_MAPPING;
import static com.demir.core.email.entity.Email.FIND_EMAILS;

@Entity
@Table(name = "EMAILS", schema = "PUBLIC")
@NamedNativeQueries(
        @NamedNativeQuery(name = FIND_EMAILS, query = "SELECT t.email AS email_address, COUNT(t.email) AS total_count FROM public.emails t GROUP BY t.email", resultSetMapping = EMAIL_INFO_MAPPING)
)
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
    public static final String FIND_EMAILS = PREFIX + "findEmails";
    public static final String EMAIL_INFO_MAPPING = "EmailInfoMapping";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    public Email() {
    }

    public Email(String email) {
        this.email = email;
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
}
