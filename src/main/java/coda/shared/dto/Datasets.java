package coda.shared.dto;

import java.util.List;
import java.util.LinkedList;

public class Datasets {
    private List<Dataset> datasets;

    public Datasets() {
        datasets = new LinkedList<Dataset>();
    }
    
    public List<Dataset> getDatasets() { return datasets; }
}

