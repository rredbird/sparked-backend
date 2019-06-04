package coda.shared.dto;

public class EvaluationMetricDto {
    private String id;
    private Boolean highValueBetter;
    private Boolean isScalarMetric;
    private Boolean isClassSpecific;

    public EvaluationMetricDto() {
        id = "";
        highValueBetter = true;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public Boolean getHighValueBetter() { return highValueBetter; }
    public void setHighValueBetter(Boolean highValueBetter) { this.highValueBetter = highValueBetter; }

    public Boolean getScalarMetric() { return isScalarMetric; }
    public void setScalarMetric(Boolean isScalarMetric) { this.isScalarMetric = isScalarMetric; }

    public Boolean getIsClassSpecific() { return isClassSpecific; }
    public void setIsClassSpecific(Boolean isClassSpecific) { this.isClassSpecific = isClassSpecific; }
}


