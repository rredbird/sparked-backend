package coda.model.order;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.logging.ILogging;
import coda.shared.dto.OrderStatusDto;
import coda.shared.dto.ClassifierDto;
import coda.shared.dto.OrderDto;
import coda.shared.interfaces.IDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements IDto<Order, OrderDto> {
    @Autowired
    private ILogging log;

    // private List<Task> tasks;
    private UUID id;
    private String name = "";
    private String orderStatus = "NEW";
    private List<Classifier> classifiers;

    public Order() {
        // tasks = new LinkedList<Task>();
        id = UUID.randomUUID();
        classifiers = new LinkedList<Classifier>();
    }

    public Order(UUID id) {
        this();
        this.id = id;
    }

    public Order(OrderDto orderDto) {
        this();
        id = orderDto.getId();
        fromDto(orderDto);
    }

    public Order(String orderAsJsonString) {
        Order order = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            order = mapper.readValue(orderAsJsonString, Order.class);
        } catch (JsonGenerationException e) {
            log.exception(e);
        } catch (JsonMappingException e) {
            log.exception(e);       
        } catch (IOException e) {
            log.exception(e);
        }

        this.setId(order.getId());
        this.setName(order.getName());
        this.setClassifiers(order.getClassifiers());
        this.setOrderStatus(order.getOrderStatus());
    }

    @JsonProperty("_id")
    public UUID getId() { return id; }
    @JsonProperty("_id")
    private void setId(UUID id) { this.id = id; }
    
    // public List<Task> getTasks() { return tasks; }

    public String pause() {
        // for (Task task: tasks) {
        //     if(task.isRunning() || task.isFinished()) {
        //         return "Started orders may not be paused.";
        //     }
        //     if(task.isPaused()) {
        //         return "Order is already paused.";
        //     }
        // }
        // for (Task task: tasks) {
        //     task.pause();
        // }
        return "OK";
    }
    
    public String carryOn() {
        // for (Task task: tasks) {
        //     if(task.isPaused()) {
        //         for (Task t: tasks) {
        //             t.carryOn();
        //         }
        //         return "OK";
        //     } else {
        //         return "Order is not paused.";
        //     }
        // }
        return "OK";
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }
    
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    public String getOrderStatus() { return this.orderStatus; }

    public void setClassifiers(List<Classifier> classifiers) { this.classifiers = classifiers; }
    public List<Classifier> getClassifiers() { return this.classifiers; }

    @Override
    @JsonIgnore
    public Order fromDto(OrderDto dto) {
        this.name = dto.getName();
        this.orderStatus = dto.getStatus();
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
        dto.setStatus(this.getOrderStatus());

        List<ClassifierDto> classifierDtos = new LinkedList<>();
        for (Classifier classifier : this.classifiers) {
            classifierDtos.add(classifier.toDto());
        }
        dto.setClassifiers(classifierDtos);

        return dto;
    }

    @JsonIgnore
    public String getJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = "";
        try {
            orderJson = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException jsonProcessingException) {
            log.exception(jsonProcessingException);
        }

        return orderJson;
    }
}