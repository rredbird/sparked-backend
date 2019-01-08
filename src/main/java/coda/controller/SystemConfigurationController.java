package coda.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coda.shared.dto.*;
import coda.database.DataLayer;
import coda.configurationService.IConfigurationService;
import coda.evaluationService.IEvaluationService;
import coda.shared.logging.ILogging;
import coda.shared.properties.Properties;
import coda.order.Order;

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
        dataLayer.writeGreeting(new Greeting("Hello Coda", 0));
        Greeting greeting = dataLayer.readGreeting(0);
        greeting.add(linkTo(methodOn(SystemConfigurationController.class).start()).withSelfRel());
        greeting.add(linkTo(methodOn(OrderController.class).getOrders()).withRel("GetOrders"));
        //greeting.add(linkTo(methodOn(EvaluationController.class).evaluation()).withRel("Evaluation"));
        
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}

