package com.order.service.controller;

import com.order.service.dto.OrderDto;
import com.order.service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping("/create")
    public String placeOrder(@RequestBody OrderDto orderDto) {
        return service.placeOrder(orderDto);
    }
}
