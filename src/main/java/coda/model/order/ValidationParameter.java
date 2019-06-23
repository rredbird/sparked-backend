package coda.model.order;

public class ValidationParameter {
    private String name;
    private String doc;
    private String value;

    public ValidationParameter() {
        name = "";
        doc = "";
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getDoc() { return doc; }
    public void setDoc(String doc) { this.doc = doc; }
}

