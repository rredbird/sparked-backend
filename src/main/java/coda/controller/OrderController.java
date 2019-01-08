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
import coda.shared.properties.Properties;

@RestController
@Component
@CrossOrigin(origins = Properties.CorsOriginAdress)
public class OrderController {
    @Autowired
    private DataLayer dataLayer;

    @Autowired
    private IEvaluationService evaluationService;

    @Autowired
    private ILogging log;

    public OrderController() {
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return dataLayer.getOrders();
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrder(@PathVariable UUID id) {
        return dataLayer.getOrder(id).getDto();
    }

    @PostMapping("/orders/save")
    public OrderDto createOrder(@RequestBody OrderDto orderData) {
        Order order = dataLayer.getOrder(orderData.getId());

        if(order == null)
        {
            order = new Order();
        }
        
        order.loadFromDto(orderData);

        return order.getDto();
    }

    @GetMapping("/orders/new")
    public OrderDto createOrder() {
        Order order = new Order();

        return order.getDto();
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

