package ru.otus.example.grooming.gsclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.example.grooming.gsclient.configuration.Constants;
import ru.otus.example.grooming.gsclient.model.dto.OrderDto;
import ru.otus.example.grooming.gsclient.model.dto.ServiceDto;
import ru.otus.example.grooming.gsclient.services.OrderControllerService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/grooming/order")
public class OrderController {

    private final OrderControllerService orderControllerService;

    @Autowired
    public OrderController(OrderControllerService orderControllerService) {
        this.orderControllerService = orderControllerService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createOrder(@RequestBody List<ServiceDto> services,
                                              HttpServletResponse httpServletResponse) {

        HttpHeaders httpHeaders = addHeaders(httpServletResponse, "CreateOrder");
        OrderDto orderDto = orderControllerService.createOrder(services);
        return new ResponseEntity<>(String.format("{\"orderNo\": %d}", orderDto.getOrder().getId()), HttpStatus.OK);

    }

    private HttpHeaders addHeaders(HttpServletResponse response, String actionName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Transact-Id", UUID.randomUUID().toString());
        headers.add("Source-App", Constants.APPLICATION_USER_TYPE);
        headers.add("Action", actionName);
        headers.forEach((k, v) -> response.addHeader(k, String.join(";", v)));
        return headers;
    }

}
