package com.demir.core.email.control;

import com.demir.core.EntityNotFoundException;
import com.demir.core.email.entity.Email;
import com.demir.core.email.entity.EmailInfo;
import com.demir.core.email.entity.EmailSummmary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static com.demir.core.email.entity.Email.FIND_ALL;
import static com.demir.core.email.entity.Email.FIND_EMAIL_INFOS;

@Service
@Transactional
public class EmailRepository {

    @PersistenceContext
    EntityManager em;

    public void update(Long id, String email) {
        Email entity = find(id);
        entity.setEmail(email);
        save(entity);
    }

    public Email find(Long id) {
        Email entity = em.find(Email.class, id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return entity;
    }

    public void delete(Long id) {
        em.remove(find(id));
    }

    public void save(Email email) {
        em.merge(email);
    }

    public List<Email> findAll() {
        return em.createNamedQuery(FIND_ALL, Email.class).getResultList();
    }

    public Email save(final String email) {
        return em.merge(new Email(email));
    }

    public void save(final List<String> emails) {
        if (emails != null && !emails.isEmpty()) {
            emails.forEach(e -> save(e));
        }
    }

    public EmailSummmary summaryReport() {
        EmailSummmary emailSummmary = new EmailSummmary();
        List<EmailInfo> resultList = em.createNamedQuery(FIND_EMAIL_INFOS).getResultList();
        emailSummmary.setEmailInfos(resultList);
        return emailSummmary;
    }

}
