package coda.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultParameterDto {
    private String name;
    private String doc;
    private String value;
    private String paramType;
    private String javaType;

    public ResultParameterDto() {
    }

    public void setName(String name) { this.name = name; };
    public void setValue(String value) { this.value = value; };

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}


