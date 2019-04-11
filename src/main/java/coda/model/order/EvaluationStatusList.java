package coda.model.order;

import coda.shared.EvaluationStatusListDeserializer;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = EvaluationStatusListDeserializer.class)
public class EvaluationStatusList {

    public EvaluationStatusList() {
    }

    private HashMap<UUID, String> statusList = new HashMap<UUID,String>();

    public String getStatus(UUID orderId) { return statusList.get(orderId); }
    public void changeStatus(UUID orderId, String status) { this.statusList.replace(orderId, status); }
    public void insertStatus(UUID orderId, String status) { this.statusList.put(orderId, status); }
    public Boolean containsOrder(UUID orderId) { return statusList.containsKey(orderId); }
	public Set<UUID> keys() { return statusList.keySet();	}
}
