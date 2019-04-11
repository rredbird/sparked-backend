package coda.model.order;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.logging.ILogging;

public class Task {
    @Autowired
    private ILogging log;

    private UUID id;
    private Order order;

    public Task(Order order) {
        id = UUID.randomUUID();
        this.order = order;
    }

    public UUID getId() { return id; }
}


