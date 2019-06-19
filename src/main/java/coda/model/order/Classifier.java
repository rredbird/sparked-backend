package coda.model.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedList;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Classifier {
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
}

