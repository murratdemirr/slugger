package com.demir.core;


import com.demir.core.email.control.EmailRepository;
import com.demir.core.kafka.QueueConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;


@Service
public class ProcessorListener {

    private static final long TIME_PERIOD_AS_MILLISECONDS = 5 * 60 * 1000;

    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private EmailRepository repository;
    @Autowired
    private QueueConsumer queueConsumer;


    public void executeAsynchronously() {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(TIME_PERIOD_AS_MILLISECONDS);
                    taskScheduler.scheduleAtFixedRate(new BulkProcessor(repository, queueConsumer), TIME_PERIOD_AS_MILLISECONDS);
                } catch (InterruptedException e) {
                }
            }
        });
    }

}
