package coda.shared.dto;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigurationDto {
    private List<ResultParameterDto> resultParameters;
    private List<MetricDto> metrics;

    public ConfigurationDto() {
        resultParameters = new LinkedList<ResultParameterDto>();
        metrics = new LinkedList<MetricDto>();
    }

    public List<ResultParameterDto> getResultParameters() { return this.resultParameters; }
    public void setResultParameters(List<ResultParameterDto> parameters) { this.resultParameters = parameters; }

    public List<MetricDto> getMetrics() { return metrics; }
    public void setMetrics(List<MetricDto> metrics) { this.metrics = metrics; }

}