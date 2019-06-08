package coda.model.order;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.logging.ILogging;

public class Task {
    @Autowired
    private ILogging log;

    private UUID id;
    private Order order;

    private Classifier classifier;
    private Dataset dataset;
    private ValidationMethod validationMethod;
    private Metric metric;
    
    public Task(Order order) {
        id = UUID.randomUUID();
        this.order = order;
    }
    public Task() {
        id = UUID.randomUUID();
    }

    public UUID getId() { return id; }
    public Classifier getClassifier() { return this.classifier; }
    public Dataset getDataset() { return this.dataset; } 
    public ValidationMethod getValidationMethod() { return this.validationMethod; } 
    public Metric getMetric() { return this.metric; } 

    public void setId(UUID id) { 
        if(id.compareTo(UUID.fromString("00000000-0000-0000-0000-000000000000")) == 0) {
            id = UUID.randomUUID();
        }
        this.id = id; 
    }
    public void setClassifier(Classifier classifier) { this.classifier = classifier; }
    public void setDataset(Dataset dataset) { this.dataset = dataset; } 
    public void setValidationMethod(ValidationMethod validationMethod) { this.validationMethod = validationMethod; } 
    public void setMetric(Metric metric) { this.metric = metric; } 

    private String status = "";
    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return this.status; }
}


