package coda.model.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import coda.shared.dto.ClassifierDto;
import coda.shared.dto.ClassifierParameterDto;
import coda.shared.interfaces.IDto;

import java.util.LinkedList;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Classifier implements IDto<Classifier, ClassifierDto> {
    private String id;
    private String name;
    private List<ClassifierParameter> parameters;

    public Classifier() {
        id = "";
        name = "";
        parameters = new LinkedList<ClassifierParameter>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public List<ClassifierParameter> getParameters() { return parameters; }
    public void setParameters(List<ClassifierParameter> parameters) { this.parameters = parameters; }

    @Override
    @JsonIgnore
    public Classifier fromDto(ClassifierDto dto) {
        this.setId(dto.getId());
        this.setName(dto.getName());
        for (ClassifierParameterDto parameterDto : dto.getParameters()) {
            this.getParameters().add((new ClassifierParameter()).fromDto(parameterDto));
        }

        return this;
    }

    @Override
    @JsonIgnore
    public ClassifierDto toDto() {
        ClassifierDto dto = new ClassifierDto();
        dto.setId(this.id);
        dto.setName(this.name);
        List<ClassifierParameterDto> parameterDtos = new LinkedList<ClassifierParameterDto>();
        
        for (ClassifierParameter parameter : this.parameters) {
            if(parameter != null) {
                parameterDtos.add(parameter.toDto());
            }
        }

        dto.setParameters(parameterDtos);

        return dto;
    }
}

