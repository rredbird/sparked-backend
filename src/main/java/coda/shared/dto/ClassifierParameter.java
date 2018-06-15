package coda.shared.dto;

public class ClassifierParameter {
    private String name;
    private String doc;

    public ClassifierParameter() {
    }

    public void setName(String name) { this.name = name; };
    public void setDoc(String doc) { this.doc = doc; };

    public String getName() {
        return name;
    }

    public String getDoc() {
        return doc;
    }
}


