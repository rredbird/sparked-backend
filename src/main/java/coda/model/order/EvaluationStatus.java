package coda.model.order;

import java.util.UUID;

public class EvaluationStatus {

    public EvaluationStatus() {
    }

    private UUID id;
    private String status;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }

    
    @Override
    public String toString() {
        String retVal = "";

        retVal += getId() + " : " + getStatus() + "\n";

        return retVal;
    }
}
