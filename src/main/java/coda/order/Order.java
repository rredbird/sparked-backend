package coda.order;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import coda.server.SparkServer;
import coda.shared.logging.Logging;
import coda.shared.dto.OrderDto;

@JsonIgnoreProperties({"_id"})
public class Order {
    @Autowired
    private Logging log;

    private List<Task> tasks;
    private UUID id;
    private SparkServer server;

    public Order() {
        tasks = new LinkedList<Task>();
        id = UUID.randomUUID();
        server = null;
    }

    public UUID getId() { return id; }

    public List<Task> getTasks() { return tasks; }

    public SparkServer getServer() { return server; }
    public void setServer(SparkServer server) { this.server = server; }

    public OrderDto getDto() {
        OrderDto dto = new OrderDto();
        
        dto.setId(this.id);

        return dto;
    }
}

