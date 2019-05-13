package coda.shared.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private UUID id;
    private String name;
    private List<TaskDto> tasks;

    public OrderDto() {
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<TaskDto> getTasks() {
        return tasks;
    }
    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
