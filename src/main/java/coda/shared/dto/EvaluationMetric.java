package coda.shared.dto;

public class EvaluationMetric {
    private String id;
    private Boolean highValueBetter;

    public EvaluationMetric() {
        id = "";
        highValueBetter = true;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public Boolean getHighValueBetter() { return highValueBetter; }
    public void setHighValueBetter(Boolean highValueBetter) { this.highValueBetter = highValueBetter; }
}


