package com.example.server.listener;

import com.example.server.event.NewStudentEvent;
import com.example.server.service.StudentService;
import com.example.server.worker.PublisherWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    private final PublisherWorker publisherWorker;
    private final StudentService studentService;

    @Autowired
    public StudentEventListener(PublisherWorker publisherWorker, StudentService studentService) {
        this.publisherWorker = publisherWorker;
        this.studentService = studentService;
    }

    @EventListener
    public void handleNewStudentEvent(NewStudentEvent event) {
        publisherWorker.broadcastQueue(studentService.getQueue());
    }
}
