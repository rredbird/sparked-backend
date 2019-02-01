package coda.notification;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.logging.ILogging;
import coda.shared.OrderStatus;
import coda.shared.dto.NotificationDto;
import coda.shared.dto.OrderDto;

public class Notification {
    @Autowired
    private ILogging log;

    private UUID id;
    private String title;
    private String info;

    public Notification() {
        id = UUID.randomUUID();
    }

    public NotificationDto getDto() {
        NotificationDto notification = new NotificationDto(id);
        notification.setTitle(title);
        notification.setInfo(info);
        return notification;
    }
}

