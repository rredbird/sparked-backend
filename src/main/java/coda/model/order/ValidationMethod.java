package coda.model.order;

import java.util.List;

import coda.shared.dto.ValidationMethodDto;
import coda.shared.interfaces.IDto;

import java.util.LinkedList;

public class ValidationMethod implements IDto<ValidationMethod, ValidationMethodDto> {
    private String id;
    private String name;
    private List<ValidationParameter> parameters;

    public ValidationMethod() {
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
    public ValidationMethod fromDto(ValidationMethodDto dto) {
        return this;
    }

    @Override
    public ValidationMethodDto toDto() {
        ValidationMethodDto dto = new ValidationMethodDto();
        dto.setName(this.id);
        dto.setValue(this.name);
        for(ValidationParameter param : parameters) {
            dto.addParameters(param.toDto());
        }

        return dto;
    }
}


