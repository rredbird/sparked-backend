package coda.shared.dto;

public class ValidationParameter {
    private String name;
    private String doc;

    public ValidationParameter() {
        name = "";
        doc = "";
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDoc() { return doc; }
    public void setDoc(String doc) { this.doc = doc; }
}

