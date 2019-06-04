package coda.model.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import coda.shared.dto.ClassifierParameterDto;
import coda.shared.interfaces.IDto;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassifierParameter implements IDto<ClassifierParameter, ClassifierParameterDto> {
    private String name;
    private String doc;
    private String value;
    private String paramType;
    private String javaType;

    public ClassifierParameter() {
    }

    public void setName(String name) { this.name = name; };
    public void setDoc(String doc) { this.doc = doc; };
    public void setValue(String value) { this.value = value; };
    public void setParamType(String paramType) { this.paramType = paramType; };
    public void setJavaType(String javaType) { this.javaType = javaType; };

    public String getName() {
        return name;
    }

    public String getDoc() {
        return doc;
    }

    public String getValue() {
        return value;
    }

    public String getParamType() {
        return paramType;
    }

    public String getJavaType() {
        return javaType;
    }

    @Override
    public ClassifierParameter fromDto(ClassifierParameterDto dto) {
        this.setDoc(dto.getDoc());
        this.setJavaType(dto.getJavaType());
        this.setName(dto.getName());
        this.setParamType(dto.getParamType());
        this.setValue(dto.getValue());
        return this;
    }

    @Override
    public ClassifierParameterDto toDto() {
        ClassifierParameterDto dto = new ClassifierParameterDto();

        dto.setName(this.name);
        dto.setValue(this.value);

        return dto;
    }
}


