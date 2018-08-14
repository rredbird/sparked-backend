package coda.order;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.logging.ILogging;
import coda.shared.Status;

public class Task {
    @Autowired
    private ILogging log;

    private UUID id;
    private Order order;

    private Status status;

    public Task(Order order) {
        id = UUID.randomUUID();
        this.order = order;
    }

    public UUID getId() { return id; }

    public Boolean isRunning() {
        return status == Status.RUNNING;
    }
    
    public Boolean isWaiting() {
        return status == Status.WAITING;
    }

    public Boolean isFinished() {
        return status == Status.FINISHED;
    }
    
    public Boolean isPaused() {
        return status == Status.PAUSED;
    }

    public void pause() {
        status = Status.PAUSED;
    }

    public void carryOn() {
        status = Status.WAITING;
    }
}


