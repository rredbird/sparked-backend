package coda.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import coda.shared.dto.*;
import coda.database.DataLayer;
import coda.configurationService.IConfigurationService;
import coda.shared.logging.ILogging;
import coda.shared.properties.Properties;

@RestController
@Component
@CrossOrigin(origins = Properties.CorsOriginAdress)
public class NotificationController {
    @Autowired
    private DataLayer dataLayer;

    @Autowired
    private IConfigurationService configurationService;

    @Autowired
    private ILogging log;

    private List<NotificationDto> notificationTestList = new LinkedList<>();

    public NotificationController() {
        NotificationDto notification = new NotificationDto();
        notification.setInfo("info1");
        notification.setTitle("title1");
        notificationTestList.add(notification);

        
        notification = new NotificationDto();
        notification.setInfo("info2");
        notification.setTitle("title2");
        notificationTestList.add(notification);

        
        notification = new NotificationDto();
        notification.setInfo("info3");
        notification.setTitle("title3");
        notificationTestList.add(notification);
    }

    @GetMapping("/notifications")
    public List<NotificationDto> notifications() {
        return notificationTestList;
    }
}
