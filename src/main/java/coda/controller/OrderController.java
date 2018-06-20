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
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import coda.shared.logging.Logging;
import coda.order.Order;

@RestController
@Component
public class OrderController {
    @Autowired
    private DataLayer dataLayer;

    @Autowired
    private IEvaluationService evaluationService;

    @Autowired
    private Logging log;

    public OrderController() {
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/orders")
    public List<Order> getOrders() {
        return dataLayer.getOrders();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/orders/{id}")
    public OrderDto getOrder(@PathVariable UUID id) {
        return dataLayer.getOrder(id).getDto();
    }

    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/orders")
    public OrderDto createOrder() {
        Order order = new Order();

        dataLayer.saveOrder(order);
        
        return order.getDto();
    }
   

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/orders/{id}")
    public void editOrder(@PathVariable UUID id) {
        return;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/orders/{id}/pause")
    public String pauseOrder(@PathVariable UUID id) {
        return dataLayer.getOrder(id).pause();
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/orders/{id}/continue")
    public String continueOrder(@PathVariable UUID id) {
        return dataLayer.getOrder(id).carryOn();
    }
}


