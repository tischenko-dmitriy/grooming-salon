package ru.otus.example.grooming.gsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsclient.model.dto.OrderDto;
import ru.otus.example.grooming.gsclient.model.dto.ServiceDto;
import ru.otus.example.grooming.gsclient.repositories.OrderRepository;
import ru.otus.example.grooming.gsclient.repositories.OrderedServiceRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderedServiceRepository orderedServiceRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderedServiceRepository orderedServiceRepository) {
        this.orderRepository = orderRepository;
        this.orderedServiceRepository = orderedServiceRepository;
    }

    public OrderDto createOrder(List<ServiceDto> services) {
        return new OrderDto();
    }

}
