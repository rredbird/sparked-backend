package coda.shared.dto;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationMethodDto {
    private String name;
    private String id;
    private List<ValidationParameterDto> parameters;

    public ValidationMethodDto() {
        parameters = new LinkedList<ValidationParameterDto>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void setParameters(List<ValidationParameterDto> parameters) { this.parameters = parameters; }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public List<ValidationParameterDto> getParameters() { return this.parameters; }

	public void addParameter(ValidationParameterDto parameterDto) {
        this.parameters.add(parameterDto);
	}
}


