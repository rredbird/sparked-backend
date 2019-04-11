package coda.model.order;

import java.util.List;

import coda.shared.dto.ValidationMetricDto;
import coda.shared.interfaces.IDto;

import java.util.LinkedList;

public class ValidationMetric implements IDto<ValidationMetric, ValidationMetricDto> {
    private String id;
    private String name;
    private List<ValidationParameter> parameters;

    public ValidationMetric() {
        id = "";
        name = "";
        parameters = new LinkedList<ValidationParameter>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<ValidationParameter> getParameters() { return parameters; }
    public void setParameters(List<ValidationParameter> parameters) { this.parameters = parameters; }

    @Override
    public ValidationMetric fromDto(ValidationMetricDto dto) {
        return this;
    }

    @Override
    public ValidationMetricDto toDto() {
        ValidationMetricDto dto = new ValidationMetricDto();
        dto.setName(this.id);
        dto.setValue(this.name);

        return dto;
    }
}


