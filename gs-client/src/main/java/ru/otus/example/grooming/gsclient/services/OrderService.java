package ru.otus.example.grooming.gsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsclient.entities.OrderEntity;
import ru.otus.example.grooming.gsclient.entities.OrderedServiceEntity;
import ru.otus.example.grooming.gsclient.entities.ServiceEntity;
import ru.otus.example.grooming.gsclient.exceptions.ServiceNotFoundException;
import ru.otus.example.grooming.gsclient.model.dto.NewOrderDto;
import ru.otus.example.grooming.gsclient.model.dto.OrderDto;
import ru.otus.example.grooming.gsclient.model.dto.ServiceDto;
import ru.otus.example.grooming.gsclient.repositories.OrderRepository;
import ru.otus.example.grooming.gsclient.repositories.OrderedServiceRepository;
import ru.otus.example.grooming.gsclient.repositories.ServiceRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderedServiceRepository orderedServiceRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderedServiceRepository orderedServiceRepository,
                        ServiceRepository serviceRepository) {
        this.orderRepository = orderRepository;
        this.orderedServiceRepository = orderedServiceRepository;
        this.serviceRepository = serviceRepository;
    }

    public OrderDto createOrder(NewOrderDto newOrderDto) {

        // 1 creating order head
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate(LocalDate.now());
        orderEntity.setScheduleItemId(newOrderDto.getStartScheduleItemId());
        orderEntity.setPetId(newOrderDto.getPetId());
        orderRepository.save(orderEntity);

        // 2 creating ordered service list
        int totalTiming = 0;
        double cost = 0.0;
        for (ServiceDto service : newOrderDto.getServices()) {
            OrderedServiceEntity orderedServiceEntity = new OrderedServiceEntity();
            orderedServiceEntity.setOrderId(orderEntity.getId());
            orderedServiceEntity.setServiceId(service.getServiceId());
            orderedServiceRepository.save(orderedServiceEntity);

            ServiceEntity serviceEntity = serviceRepository.findById(service.getServiceId())
                    .orElseThrow(() -> new ServiceNotFoundException(service.getServiceName()));
            totalTiming = totalTiming + serviceEntity.getTimingMinutes();
            cost = cost + serviceEntity.getPrice();
        }

        totalTiming = (totalTiming % 60) == 0 ? totalTiming : ((totalTiming / 60) + 1) * 60;
        orderEntity.setTotalTimingMinutes(totalTiming);
        orderEntity.setTotalCost(cost);
        orderRepository.save(orderEntity);

        // 3 set time in schedule as busy


        return new OrderDto();
    }

}
