package ru.otus.example.grooming.gsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsclient.model.dto.OrderDto;
import ru.otus.example.grooming.gsclient.model.dto.ServiceDto;

import java.util.List;

@Service
public class OrderControllerService {

    private final OrderService orderService;

    @Autowired
    public OrderControllerService(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderDto createOrder(List<ServiceDto> services) {
        OrderDto orderDto = orderService.createOrder(services);
        return new OrderDto();
    }

}
