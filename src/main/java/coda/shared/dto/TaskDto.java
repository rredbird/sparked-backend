package coda.shared.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto {
    private UUID orderId;
    private UUID id;
    
    private String status;
    private ClassifierDto classifier;

    public TaskDto() {
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getOrderId() { return orderId; }
    public void setOrderId(UUID id) { this.orderId = id; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }

    public ClassifierDto getClassifier() {
        return classifier;
    }
    public void setClassifier(ClassifierDto classifier) {
        this.classifier = classifier;
    }
}
