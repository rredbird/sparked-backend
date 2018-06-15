package coda.shared.dto;

import java.util.List;
import java.util.LinkedList;

public class Validator {
    private String id;
    private String name;
    private List<ValidationParameter> parameters;

    public Validator() {
        id = "";
        name = "";
        parameters = new LinkedList<ValidationParameter>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<ValidationParameter> getParameters() { return parameters; }
    public void setParameters(List<ValidationParameter> parameters) { this.parameters = parameters; }
}


