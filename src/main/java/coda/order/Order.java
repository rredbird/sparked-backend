package coda.order;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.logging.ILogging;
import coda.shared.OrderStatus;
import coda.shared.dto.OrderDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    @Autowired
    private ILogging log;

    private List<Task> tasks;
    private UUID id;
    private String name;
    private OrderStatus orderStatus; 

    public Order() {
        tasks = new LinkedList<Task>();
        id = UUID.randomUUID();
    }

    @JsonProperty("_id")
    public UUID getId() { return id; }
    
    public List<Task> getTasks() { return tasks; }

    @JsonIgnore
    public OrderDto getDto() {
        OrderDto dto = new OrderDto();
        
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setStatus(this.orderStatus == null ? OrderStatus.WAITING.toString() : this.orderStatus.toString());

        return dto;
    }
    public void loadFromDto(OrderDto order) {
        this.name = order.getName();
        this.orderStatus = OrderStatus.valueOf(OrderStatus.class, order.getStatus());
    }

    public String pause() {
        for (Task task: tasks) {
            if(task.isRunning() || task.isFinished()) {
                return "Started orders may not be paused.";
            }
            if(task.isPaused()) {
                return "Order is already paused.";
            }
        }
        for (Task task: tasks) {
            task.pause();
        }
        return "OK";
    }
    
    public String carryOn() {
        for (Task task: tasks) {
            if(task.isPaused()) {
                for (Task t: tasks) {
                    t.carryOn();
                }
                return "OK";
            } else {
                return "Order is not paused.";
            }
        }
        return "OK";
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }
    
    public void setOrderStatus(String orderStatus) { 
        if (orderStatus == null) {
            log.error("Tried to set null as Order Status for order: " + this.id.toString() + ". Ignored!");
        } else {
            this.orderStatus = Enum.valueOf(OrderStatus.class, orderStatus); 
        }        
    }
    public OrderStatus getOrderStatus() { return this.orderStatus; }
}

