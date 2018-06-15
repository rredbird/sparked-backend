package coda.shared.dto;

public class Dataset {
    private String id;
    private String revision;
    private String description;

    public Dataset() {
        id = "";
        revision = "";
        description = "";
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRevision() { return revision; }
    public void setRevision(String revision) { this.revision = revision; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}


