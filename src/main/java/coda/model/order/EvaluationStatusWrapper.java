package coda.model.order;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder("evaluation stati")
public class EvaluationStatusWrapper {

    public EvaluationStatusWrapper() {
    }

    @JsonProperty("evaluation status")
    private List<EvaluationStatus> evaluationStatus = new LinkedList<EvaluationStatus>();    

    public List<EvaluationStatus> getEvaluationStatus() { return this.evaluationStatus; }
    public void setId(List<EvaluationStatus> evaluationStatus) { this.evaluationStatus = evaluationStatus; }

    
    @Override
    public String toString() {
        String retVal = "";

        for(EvaluationStatus wrapper : evaluationStatus) {
            retVal += wrapper.toString();
        }

        return retVal;
    }
}