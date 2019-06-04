package coda.model.order;

import java.util.List;

import coda.shared.dto.ValidationMethodDto;
import coda.shared.dto.ValidationParameterDto;
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
    public void addParameter(ValidationParameter parameter) { this.parameters.add(parameter); }

    @Override
    public ValidationMethod fromDto(ValidationMethodDto dto) {
        this.setId(dto.getId());
        this.setName(dto.getName());
        for (ValidationParameterDto parameterDto : dto.getParameters()) {
            this.addParameter(new ValidationParameter().fromDto(parameterDto));
        }


        return this;
    }

    @Override
    public ValidationMethodDto toDto() {
        ValidationMethodDto dto = new ValidationMethodDto();
        dto.setName(this.getName());
        dto.setId(this.getId());
        for (ValidationParameter parameter : this.getParameters()) {
            dto.addParameter(parameter.toDto());
        }
        
        return dto;
    }
}


