package com.demir.edge.dataset.control;

import com.demir.edge.RemoteXmlServiceException;
import com.demir.edge.dataset.entity.Dataset;
import com.demir.edge.dataset.entity.Emails;
import com.demir.edge.dataset.entity.Resources;
import com.demir.edge.kafka.QueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class DataSetManager {

    @Autowired
    private QueueSender sender;
    @Autowired
    private DataSetRemote remote;

    @Async
    public void process(Dataset entity) {
        if (entity != null) {
            final Emails emails = entity.getEmails();
            if (emails != null && !emails.getEmail().isEmpty()) {
                emails.getEmail().parallelStream().forEach(e -> {
                    sender.send(e);
                });
            }

            final Resources resources = entity.getResources();
            if (resources != null && !resources.getUrl().isEmpty()) {
                resources.getUrl().parallelStream().forEach(url -> {
                    process(url);
                });
            }
        }
    }

    @Async
    @Retryable(value = {RemoteXmlServiceException.class}, backoff = @Backoff(delay = 5000))
    public void process(String url) {
        Dataset dataset = null;
        try {
            dataset = remote.get(url);
        } catch (MalformedURLException e) {
        }
        if (dataset != null) {
            process(dataset);
        }
    }

    @Recover
    public void recover(RemoteXmlServiceException e) {
        System.out.println("recover... " + e.getUrl());
    }

}
