package coda.shared.dto;

import java.util.List;
import java.util.LinkedList;

public class Datasets {
    private List<DatasetDto> datasets;

    public Datasets() {
        datasets = new LinkedList<DatasetDto>();
    }
    
    public List<DatasetDto> getDatasets() { return datasets; }
}

