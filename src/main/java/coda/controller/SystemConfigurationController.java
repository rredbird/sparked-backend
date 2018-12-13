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
import coda.order.Order;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@Component
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/orders/{id}")
    public OrderDto getOrder(@PathVariable UUID id) {
        return dataLayer.getOrder(id).getDto();
    }

    @PostMapping("/orders")
    public OrderDto createOrder(@RequestBody OrderDto orderData) {
        Order order = new Order();

        dataLayer.saveOrder(order);
        
        return order.getDto();
    }

    @PutMapping("/orders/{id}")
    public void editOrder(@PathVariable UUID id) {
        return;
    }

    @PatchMapping("/orders/{id}/pause")
    public String pauseOrder(@PathVariable UUID id) {
        return dataLayer.getOrder(id).pause();
    }
    
    @PatchMapping("/orders/{id}/continue")
    public String continueOrder(@PathVariable UUID id) {
        return dataLayer.getOrder(id).carryOn();
    }
}

