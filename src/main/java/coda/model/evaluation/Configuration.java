package coda.model.evaluation;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Configuration {
    // private List<ResultParameter> resultParameters; ignore parameter at this time...
    private List<EvaluationResultMetric> metrics;

    public Configuration() {
        //resultParameters = new LinkedList<ResultParameter>();
        metrics = new LinkedList<EvaluationResultMetric>();
    }

    // public List<ResultParameter> getResultParameters() { return this.resultParameters; }
    // public void setResultParameters(List<ResultParameter> parameters) { this.resultParameters = parameters; }

    public List<EvaluationResultMetric> getMetrics() { return metrics; }
    public void setMetrics(List<EvaluationResultMetric> metrics) { this.metrics = metrics; }
}


