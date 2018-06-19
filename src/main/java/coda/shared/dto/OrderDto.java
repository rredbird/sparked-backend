package coda.shared.dto;

import java.util.Date;
import java.util.UUID;

public class OrderDto {
    private UUID id;

    public OrderDto() {
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

}
