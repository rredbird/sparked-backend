package coda.model.order;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EvaluationStatusList {

    public EvaluationStatusList() {
    }

    @JsonProperty("evaluation stati")
    private List<EvaluationStatusWrapper> statusList = new LinkedList<EvaluationStatusWrapper>();

    HashMap<UUID, String> orderIdToStatusMap = new HashMap<UUID, String>();

    public void buildOrderToStatusMap() {
        for(EvaluationStatusWrapper wrapper : statusList) {
            for(EvaluationStatus status : wrapper.getEvaluationStatus()) {
                orderIdToStatusMap.put(status.getId(), status.getStatus());
            }
        }
    }

    public List<EvaluationStatusWrapper> getStatusList() { return this.statusList; }
    public void getStatusList(List<EvaluationStatusWrapper> statusList) { this.statusList = statusList; }

    public String getStatus(UUID orderId) { 
        if(!containsOrder(orderId)) {
            return null;
        }
        return orderIdToStatusMap.get(orderId);
    }

    public Boolean containsOrder(UUID orderId) { 
        return orderIdToStatusMap.containsKey(orderId);
    }

    @Override
    public String toString() {
        String retVal = "";

        for(EvaluationStatusWrapper wrapper : statusList) {
            retVal += wrapper.toString();
        }

        return retVal + "\n";
    }
}
    