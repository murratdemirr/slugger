package com.demir.core.email.control;

import com.demir.core.email.entity.Email;
import com.demir.core.email.entity.EmailInfo;
import com.demir.core.email.entity.EmailSummmary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static com.demir.core.email.entity.Email.FIND_EMAILS;

@Service
@Transactional
public class EmailRepository {

    @PersistenceContext
    EntityManager em;

    public void save(final String email) {
        em.persist(new Email(email));
    }

    public void save(final List<String> emails) {
        if (emails != null && !emails.isEmpty()) {
            emails.forEach(e -> save(e));
        }
    }

    public EmailSummmary summmaryReport() {
        EmailSummmary emailSummmary = new EmailSummmary();
        List<EmailInfo> resultList = em.createNamedQuery(FIND_EMAILS).getResultList();
        emailSummmary.setEmailInfos(resultList);
        return emailSummmary;
    }

}
