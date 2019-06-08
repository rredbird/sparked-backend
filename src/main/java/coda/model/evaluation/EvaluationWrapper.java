package coda.model.evaluation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EvaluationWrapper {
    private Evaluation evaluation;
    
    public EvaluationWrapper() {
    }

    public Evaluation getEvaluation() { return evaluation; }
    public void setEvaluation(Evaluation evaluation) { this.evaluation = evaluation; }
}