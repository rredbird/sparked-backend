package coda.model.order;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import coda.shared.dto.ClassifierParameterDto;
import coda.shared.dto.ConfigurationDto;
import coda.shared.dto.MetricDto;
import coda.shared.dto.ResultParameterDto;
import coda.shared.interfaces.IDto;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Configuration implements IDto<Configuration, ConfigurationDto> {
    private List<ResultParameter> resultParameters;
    private List<Metric> metrics;

    public Configuration() {
        resultParameters = new LinkedList<ResultParameter>();
        metrics = new LinkedList<Metric>();
    }

    public List<ResultParameter> getResultParameters() { return this.resultParameters; }
    public void setResultParameters(List<ResultParameter> parameters) { this.resultParameters = parameters; }

    public List<Metric> getMetrics() { return metrics; }
    public void setMetrics(List<Metric> metrics) { this.metrics = metrics; }

    @Override
    public Configuration fromDto(ConfigurationDto dto) {
        return null;
    }

    @Override
    public ConfigurationDto toDto() {
        ConfigurationDto retVal = new ConfigurationDto();

        List<ResultParameterDto> dtoList = new LinkedList<ResultParameterDto>();
        for (ResultParameter dto : resultParameters) {
            dtoList.add(dto.toDto());            
        }
        retVal.setResultParameters(dtoList);
        List<MetricDto> metricDtoList = new LinkedList<MetricDto>();
        for (Metric dto : metrics) {
            metricDtoList.add(dto.toDto());            
        }
        retVal.setMetrics(metricDtoList);

        return retVal;
    }
}


