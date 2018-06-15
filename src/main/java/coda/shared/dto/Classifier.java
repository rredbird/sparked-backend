package coda.shared.dto;

import java.util.List;
import java.util.LinkedList;


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

