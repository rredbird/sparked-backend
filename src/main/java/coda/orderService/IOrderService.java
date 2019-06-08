package coda.orderService;

import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import coda.model.order.Order;

import coda.model.evaluation.Evaluations;
import coda.shared.dto.OrderResultDto;

@Component("evaluationService")
public interface IOrderService {
    /**
     * return values are 
     * Running
     * Finished
     * Error
     * more?
     */
    public String GetStatus(UUID taskId);

    public String GetOutput(UUID taskId);

    public List<Order> getOrders(Boolean includeUnknownOrders);

	public Order getOrder(UUID id);

	public void saveOrder(Order order);

	public Evaluations getResult(UUID id);

	public void startOrder(Order order);
}

