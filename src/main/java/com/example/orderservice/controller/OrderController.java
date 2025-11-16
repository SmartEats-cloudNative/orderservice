package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderFromFE;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping("/saveOrderToDb")
    public ResponseEntity<OrderDto> saveOrderToDb(@RequestBody OrderFromFE orderFromFE) {
        OrderDto Result= orderService.saveOrderToDb(orderFromFE);
        return new ResponseEntity<>(Result, HttpStatus.OK);
    }
}
