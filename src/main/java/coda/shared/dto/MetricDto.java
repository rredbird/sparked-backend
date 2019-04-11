package coda.shared.dto;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import coda.shared.dto.ClassifierParameterDto;
import coda.shared.dto.ResultParameterDto;
import coda.shared.interfaces.IDto;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetricDto {
    private String id;
    private List<List<Double>> raw;
    private List<Double> mean;
    private List<Double> var;
    private List<Double> max;
    private List<Double> min;

    public MetricDto() {
        mean = new LinkedList<Double>();
        var = new LinkedList<Double>();
        max = new LinkedList<Double>();
        min = new LinkedList<Double>();
        raw = new LinkedList<List<Double>>();
    }

    public void setId(String id) { this.id = id; };
    public void setRaw(List<List<Double>> raw) { this.raw = raw; };
    public void setMean(List<Double> mean) { this.mean = mean; };
    public void setVar(List<Double> var) { this.var = var; };
    public void setMax(List<Double> max) { this.max = max; };
    public void setMin(List<Double> min) { this.min = min; };

    public String getId() {
        return id;
    }
    public List<List<Double>> getRaw() {
        return raw;
    }
    public List<Double> getMean() {
        return mean;
    }
    public List<Double> getVar() {
        return mean;
    }
    public List<Double> getMax() {
        return mean;
    }
    public List<Double> getMin() {
        return mean;
    }
}


