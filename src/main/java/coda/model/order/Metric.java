package coda.model.order;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Metric {
    private String id;
    private Boolean highValueBetter;
    private Boolean isScalarMetric;
    private Boolean isClassSpecific;

    public Metric() {
    }

    public void setId(String id) { this.id = id; };
    public String getId() {
        return id;
    }
    
    public Boolean getHighValueBetter() { return highValueBetter; }
    public void setHighValueBetter(Boolean highValueBetter) { this.highValueBetter = highValueBetter; }

    public Boolean getScalarMetric() { return isScalarMetric; }
    public void setScalarMetric(Boolean isScalarMetric) { this.isScalarMetric = isScalarMetric; }

    public Boolean getIsClassSpecific() { return isClassSpecific; }
    public void setIsClassSpecific(Boolean isClassSpecific) { this.isClassSpecific = isClassSpecific; }
}


