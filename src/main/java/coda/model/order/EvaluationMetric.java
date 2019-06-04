package coda.model.order;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import coda.shared.dto.EvaluationMetricDto;
import coda.shared.interfaces.IDto;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EvaluationMetric implements IDto<EvaluationMetric, EvaluationMetricDto> {
    private String id;
    private Boolean highValueBetter;
    private Boolean isScalarMetric;
    private Boolean isClassSpecific;

    public EvaluationMetric() {
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
    
    @Override
    public EvaluationMetric fromDto(EvaluationMetricDto dto) {
        this.highValueBetter = dto.getHighValueBetter();
        this.id = dto.getId();

        return this;
    }

    @Override
    public EvaluationMetricDto toDto() {
        EvaluationMetricDto dto = new EvaluationMetricDto();

        dto.setId(this.getId());
        dto.setHighValueBetter(this.getHighValueBetter());
        
        return dto;
    }
}


