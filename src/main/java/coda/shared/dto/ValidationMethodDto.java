package coda.shared.dto;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import coda.model.order.ValidationParameter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationMethodDto {
    private String name;
    private String value;
    private List<ValidationParameterDto> parameters;

    public ValidationMethodDto() {
        parameters = new LinkedList<ValidationParameterDto>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

	public void addParameters(ValidationParameterDto parameterDto) {
        this.parameters.add(parameterDto);
	}
}


