package coda.notification;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import coda.shared.logging.ILogging;
import coda.shared.dto.NotificationDto;

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

