package coda.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import coda.shared.dto.*;
import coda.database.DataLayer;
import coda.shared.logging.ILogging;
import coda.shared.properties.Properties;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@Component
@CrossOrigin(origins = Properties.CorsOriginAdress)
public class SystemConfigurationController {
    @Autowired
    private DataLayer dataLayer;

    @Autowired      
    private ILogging log;

    public SystemConfigurationController() {
    }

    @GetMapping("/database")
    public HttpEntity<SystemConfigurationData> getDatabase() {
        SystemConfigurationData systemConfigurationData = new SystemConfigurationData("hello", "world");
        systemConfigurationData.add(linkTo(methodOn(SystemConfigurationController.class).getDatabase()).withSelfRel());

        return new ResponseEntity<>(systemConfigurationData, HttpStatus.OK);
    }

    @GetMapping("/start")
    public HttpEntity<Greeting> start() {
        Greeting greeting = new Greeting("Welcome to Coda", 0);
        greeting.add(linkTo(methodOn(SystemConfigurationController.class).start()).withSelfRel());
        greeting.add(linkTo(methodOn(OrderController.class).getOrders()).withRel("GetOrders"));
        greeting.add(linkTo(methodOn(TestController.class).selfTest()).withRel("selfTest"));

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}

