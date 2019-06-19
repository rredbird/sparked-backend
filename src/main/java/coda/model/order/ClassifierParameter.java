package coda.model.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassifierParameter {
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
}


