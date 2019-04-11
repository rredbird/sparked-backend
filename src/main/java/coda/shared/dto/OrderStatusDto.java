package coda.shared.dto;

import coda.shared.ResultDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ResultDeserializer.class)
public class OrderStatusDto {

    public OrderStatusDto() {
    }

    private String id;
    private String status;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }
}
