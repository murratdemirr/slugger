package com.demir.edge.dataset.control;

import com.demir.edge.RemoteXmlServiceException;
import com.demir.edge.dataset.entity.DatasetType;
import com.demir.edge.dataset.entity.EmailsType;
import com.demir.edge.dataset.entity.ResourcesType;
import com.demir.edge.kafka.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DataSetManager {

    @Autowired
    Sender sender;

    @Async
    public void process(DatasetType entity) {
        if (entity != null) {
            final EmailsType emails = entity.getEmails();
            if (emails != null && !emails.getEmail().isEmpty()) {
                emails.getEmail().parallelStream().forEach(e -> {
                    sender.send(e);
                });
            }

            final ResourcesType resources = entity.getResources();
            if (resources != null && !resources.getUrl().isEmpty()) {
                resources.getUrl().parallelStream().forEach(url -> {
                    process(url);
                });
            }
        }
    }

    @Async
    @Retryable(value = {RemoteXmlServiceException.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public void process(String url) {
        System.out.println(url);
        throw new RemoteXmlServiceException(url);
    }

    @Recover
    public void recover(RemoteXmlServiceException e) {
        System.out.println("recover... " + e.getUrl());
        // ... panic
    }

}
