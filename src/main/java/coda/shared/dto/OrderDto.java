package coda.shared.dto;

import java.util.UUID;

import coda.shared.OrderStatus;

public class OrderDto {
    private UUID id;
    private String name;
    private OrderStatus status;

    public OrderDto() {
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return this.status.toString(); }
    public void setStatus(String status) { this.status = OrderStatus.valueOf(status); }
}
