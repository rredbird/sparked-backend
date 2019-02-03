package coda.shared.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import coda.shared.OrderStatus;

@JsonIgnoreProperties({"_id"})
public class OrderDto {
    private UUID id;
    private String name;
    private OrderStatus status;
    private List<ClassifierDto> classifiers;

    public OrderDto() {
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return this.status.toString(); }
    public void setStatus(String status) { 
        this.status = OrderStatus.valueOf(status); 
    }

    public List<ClassifierDto> getClassifiers() {
        return classifiers;
    }
    public void setClassifiers(List<ClassifierDto> classifiers) {
        this.classifiers = classifiers;
    }
}
