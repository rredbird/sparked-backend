package coda.shared.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassifierDto {
    private String id;
    private String name;
    private List<ClassifierParameterDto> parameters;

    public ClassifierDto() {
        id = "";
        name = "";
        parameters = new LinkedList<ClassifierParameterDto>();
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public List<ClassifierParameterDto> getParameters() { return parameters; }
    public void setParameters(List<ClassifierParameterDto> parameters) { this.parameters = parameters; }
}

