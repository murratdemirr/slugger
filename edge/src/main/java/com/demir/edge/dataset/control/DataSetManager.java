package com.demir.edge.dataset.control;

import com.demir.edge.dataset.entity.Dataset;
import com.demir.edge.dataset.entity.Emails;
import com.demir.edge.dataset.entity.Resources;
import com.demir.edge.kafka.QueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DataSetManager {

    @Autowired
    private QueueSender sender;
    @Autowired
    private DataSetRemote remote;

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
                    try {
                        Dataset dataset = remote.find(url);
                        if (dataset != null) {
                            process(dataset);
                        }
                    } catch (Exception e) {
                    }
                });
            }
        }
    }


}
