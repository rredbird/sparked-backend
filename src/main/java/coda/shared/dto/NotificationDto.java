package coda.shared.dto;

import java.util.UUID;

public class NotificationDto {
    private final UUID id;
    private String title;
    private String info;

    public NotificationDto() {
        id = UUID.randomUUID();
        title = "";
        info = "";
    }
    public NotificationDto(UUID id) {
        this.id = id;
        title = "";
        info = "";
    }

    public String getId() { return id.toString(); }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }

}

