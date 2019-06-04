package coda.shared.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import coda.model.order.Task;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private UUID id;
    private String name;
    private List<Task> tasks;
    private String status;

    public OrderDto() {
    }

    public UUID getId() { 
        return id; 
    }
    public void setId(UUID id) { 
        this.id = id; 
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
