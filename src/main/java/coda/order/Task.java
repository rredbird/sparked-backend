package coda.order;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.logging.ILogging;
import coda.shared.OrderStatus;

public class Task {
    @Autowired
    private ILogging log;

    private UUID id;
    private Order order;

    private OrderStatus status;

    public Task(Order order) {
        id = UUID.randomUUID();
        this.order = order;
    }

    public UUID getId() { return id; }

    public Boolean isRunning() {
        return status == OrderStatus.RUNNING;
    }
    
    public Boolean isWaiting() {
        return status == OrderStatus.WAITING;
    }

    public Boolean isFinished() {
        return status == OrderStatus.FINISHED;
    }
    
    public Boolean isPaused() {
        return status == OrderStatus.PAUSED;
    }

    public void pause() {
        status = OrderStatus.PAUSED;
    }

    public void carryOn() {
        status = OrderStatus.WAITING;
    }
}


