package coda.model.evaluation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultParameter {
    private String name;
    private String doc;
    private String value;
    private String paramType;
    private String javaType;

    public ResultParameter() {
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


