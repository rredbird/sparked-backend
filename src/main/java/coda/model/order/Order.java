package coda.model.order;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.logging.ILogging;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    @Autowired
    private ILogging log;

    private List<Task> tasks;
    private UUID _id;
    private UUID evaluationId;
    private String name = "";
    private String status = "";
    
    public Order() {
        tasks = new LinkedList<Task>();
        _id = UUID.randomUUID();
    }

    public Order(UUID mongoId) {
        this();
        this._id = mongoId;
    }

    public Order(String orderAsJsonString) {
        this();
        if(orderAsJsonString == null || orderAsJsonString == "") {
            return;
        }
        Order order = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            order = mapper.readValue(orderAsJsonString, Order.class);
        } catch (JsonGenerationException e) {
            log.exception(e);
        } catch (JsonMappingException e) {
            log.exception(e);       
        } catch (IOException e) {
            log.exception(e);
        }

        this.set_Id(order.get_Id());
        this.setEvaluationId(order.getEvaluationId());
        this.setName(order.getName());
        this.setTasks(order.getTasks());
        this.setStatus(order.getStatus());
    }

    /// Mongo DB ID
    @JsonProperty("_id")
    public UUID get_Id() { return _id; }
    @JsonProperty("_id")
    public void set_Id(UUID id) { 
        if(id.compareTo(UUID.fromString("00000000-0000-0000-0000-000000000000")) == 0) {
            id = UUID.randomUUID();
        }
        this._id = id; 
    }
    
    /// Coda Evaluation ID
    public UUID getEvaluationId() { return evaluationId; }
    public void setEvaluationId(UUID evaluationId) { this.evaluationId = evaluationId; }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public List<Task> getTasks() { return this.tasks; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }

    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return this.status; }

    @JsonIgnore
    public String getJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = "";
        try {
            orderJson = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException jsonProcessingException) {
            log.exception(jsonProcessingException);
        }

        return orderJson;
    }
}