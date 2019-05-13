package coda.model.order;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EvaluationStatusList {

    public EvaluationStatusList() {
    }

    @JsonProperty("evaluation stati")
    private List<EvaluationStatusWrapper> statusList = new LinkedList<EvaluationStatusWrapper>();

    public List<EvaluationStatusWrapper> getStatusList() { return this.statusList; }
    public void getStatusList(List<EvaluationStatusWrapper> statusList) { this.statusList = statusList; }

    public String getStatus(UUID orderId) { 
        for (EvaluationStatusWrapper statusWrapper : statusList) {
            for (EvaluationStatus status : statusWrapper.getEvaluationStatus()) {
                if(status.getId() == orderId) {
                    return status.getStatus();
                }
            }
        }
        return null;
    }

    public Boolean containsOrder(UUID orderId) { 
        for (EvaluationStatusWrapper statusWrapper : statusList) {
            for (EvaluationStatus status : statusWrapper.getEvaluationStatus()) {
                if(status.getId() == orderId) {
                    return true;
                }
            }
        }
        return false;
    }
}
