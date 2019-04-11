package coda.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import coda.shared.dto.*;
import coda.orderService.IOrderService;
import coda.shared.logging.ILogging;
import coda.model.order.Order;
import coda.shared.properties.Properties;

@RestController
@Component
@CrossOrigin(origins = Properties.CorsOriginAdress)
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private ILogging log;

    public OrderController() {
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        List<OrderDto> retVal = new LinkedList<OrderDto>();
        for(Order order : orderService.getOrders(true)) {
            retVal.add(order.toDto());
        }
        return retVal;
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrder(@PathVariable UUID id) {
        return orderService.getOrder(id).toDto();
    }

    @GetMapping("/orders/{id}/result")
    public OrderResultDto getResult(@PathVariable UUID id) {
        OrderResultDto retVal = orderService.getResult(id).toDto();
        
        return retVal;
    }

    @PostMapping("/orders/save")
    public Order createOrder(@RequestBody OrderDto orderData) {
        Order order = orderService.getOrder(orderData.getId());

        if(order == null)
        {
            order = new Order(orderData);
        } else {
            order.fromDto(orderData);
        }

        orderService.saveOrder(order);

        return order;
    }

    @GetMapping("/orders/new")
    public Order createOrder() {
        Order order = new Order();

        return order;
    }

    @PatchMapping("/orders/{id}/pause")
    public String pauseOrder(@PathVariable UUID id) {
        return orderService.getOrder(id).pause();
    }
    
    @PatchMapping("/orders/{id}/continue")
    public String continueOrder(@PathVariable UUID id) {
        return orderService.getOrder(id).carryOn();
    }
}

