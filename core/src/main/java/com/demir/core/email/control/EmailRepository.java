package com.demir.core.email.control;

import com.demir.core.email.entity.Email;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmailRepository {

    @PersistenceContext
    EntityManager em;

    public void save(final String email) {
        em.persist(new Email(email));
    }

    public void save(final List<String> emails) {
        if (emails != null && !emails.isEmpty()) {
            System.out.println("Count: " + emails.size());
            emails.forEach(e -> save(e));
        }
    }


}
