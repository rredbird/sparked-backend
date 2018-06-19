package coda.order;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.logging.Logging;

public class Task {
    @Autowired
    private Logging log;

    private UUID id;
    private Order order;

    public Task(Order order) {
        id = UUID.randomUUID();
        this.order = order;
    }

    public UUID getId() { return id; }
}


