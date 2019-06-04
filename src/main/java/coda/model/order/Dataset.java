package coda.model.order;

public class Dataset
{
    private String id;
    private String revision;
    private String description;
    private Integer instances;
    private Integer classes;
    private Integer features;

    public Dataset() {
        id = "";
        revision = "";
        description = "";
        classes = -1;
        features = -1;
        instances = -1;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRevision() { return revision; }
    public void setRevision(String revision) { this.revision = revision; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getClasses() { return classes; }
    public void setClasses(Integer classes) { this.classes = classes; }
    
    public Integer getFeatures() { return features; }
    public void setFeatures(Integer features) { this.features = features; }

    public Integer getInstances() { return instances; }
    public void setInstances(Integer instances) { this.instances = instances; }
}


