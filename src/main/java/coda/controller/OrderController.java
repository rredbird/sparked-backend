package coda.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import coda.orderService.IOrderService;
import coda.shared.logging.ILogging;
import coda.model.evaluation.Evaluations;
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
    public List<Order> getOrders() {
        return orderService.getOrders(true);
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable UUID mongoId) {
        return orderService.getOrder(mongoId);
    }

    @GetMapping("/orders/{id}/result")
    public Evaluations getResult(@PathVariable UUID id) {
        Evaluations retVal = orderService.getResult(id);
        
        return retVal;
    }

    @PostMapping("/orders/save")
    public Order saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);

        return order;
    }

    @PostMapping("/orders/start")
    public Order startOrder(@RequestBody Order order) {
        orderService.startOrder(order);
        orderService.saveOrder(order);

        return order;
    }

    @GetMapping("/orders/new")
    public Order createOrder() {
        Order order = new Order();

        return order;
    }
}

