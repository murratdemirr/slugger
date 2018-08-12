package com.demir.core;

import com.demir.core.email.control.EmailRepository;
import com.demir.core.email.entity.Email;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailRepositoryTest {

    @Autowired
    private EmailRepository repository;

    @Test
    public void crud() {
        Email email = new Email();
        email.setEmail("murat.demir@gmail.com");
        email.setBatchId(UUID.randomUUID().toString());
        email.setEncounteredTimes(Long.valueOf(1));
        email = repository.save(email);
        Email fromDb = repository.find(email.getId());
        Assert.assertNotNull(fromDb);
        Assert.assertEquals(email.getEmail(), fromDb.getEmail());
        repository.update(email.getId(), "murat.demir@google.com");
        fromDb = repository.find(email.getId());
        Assert.assertEquals("murat.demir@google.com", fromDb.getEmail());
        Long count = repository.countByEmail("murat.demir@google.com");
        Assert.assertEquals(Long.valueOf(1), count);
        repository.delete(email.getId());
        try {
            fromDb = repository.find(email.getId());
        }catch (EntityNotFoundException ex) {
            fromDb = null;
        }
        Assert.assertEquals(null, fromDb);
    }

}
