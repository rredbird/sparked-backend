package coda.order;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.logging.ILogging;
import coda.shared.OrderStatus;
import coda.shared.dto.ClassifierDto;
import coda.shared.dto.OrderDto;
import coda.shared.interfaces.IDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order implements IDto<Order, OrderDto> {
    @Autowired
    private ILogging log;

    private List<Task> tasks;
    private UUID id;
    private String name;
    private OrderStatus orderStatus;
    private List<Classifier> classifiers;

    public Order() {
        tasks = new LinkedList<Task>();
        id = UUID.randomUUID();
        classifiers = new LinkedList<Classifier>();
    }

    public Order(OrderDto orderDto) {
        this();
        id = orderDto.getId();
        fromDto(orderDto);
    }

    @JsonProperty("_id")
    public UUID getId() { return id; }
    
    public List<Task> getTasks() { return tasks; }

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

    public void setClassifiers(List<Classifier> classifiers) { this.classifiers = classifiers; }
    public List<Classifier> getClassifiers() { return this.classifiers; }

    @Override
    @JsonIgnore
    public Order fromDto(OrderDto dto) {
        this.name = dto.getName();
        this.orderStatus = OrderStatus.valueOf(OrderStatus.class, dto.getStatus());
        this.setClassifiers(new LinkedList<Classifier>());
        for (ClassifierDto classifierDto : dto.getClassifiers()) {
            this.classifiers.add(new Classifier().fromDto(classifierDto));
        }

        return this;
    }

    @Override
    @JsonIgnore
    public OrderDto toDto() {
        OrderDto dto = new OrderDto();
        
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setStatus(this.orderStatus == null ? OrderStatus.WAITING.toString() : this.orderStatus.toString());

        List<ClassifierDto> classifierDtos = new LinkedList<>();
        for (Classifier classifier : this.classifiers) {
            classifierDtos.add(classifier.toDto());
        }
        dto.setClassifiers(classifierDtos);

        return dto;
    }
}