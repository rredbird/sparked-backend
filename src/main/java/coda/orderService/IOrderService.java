package coda.orderService;

import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import coda.model.order.Order;
import coda.model.order.OrderResult;
import coda.shared.dto.OrderDto;
import coda.shared.dto.OrderResultDto;

@Component("evaluationService")
public interface IOrderService {
    public void RunTask(UUID taskId);

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

	public OrderResult getResult(UUID id);
}

