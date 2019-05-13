package coda.model.order;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class EvaluationStatus {

    public EvaluationStatus() {
    }

    private UUID id;
    private String status;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public void setId(String id) { this.id = UUID.fromString(id); }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }
}
