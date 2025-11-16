package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderFromFE;
import com.example.orderservice.dto.UserDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    SequenceGenerator sequenceGenerator;
    @Autowired
    RestTemplate restTemplate;


    public OrderDto saveOrderToDb(OrderFromFE orderFromFE) {
        int newOrderId= sequenceGenerator.generateNextOrderId();
        UserDto userDto = fetchUserDetailsById(orderFromFE.getUserId());
        Order order= new Order();
        order.setOrderId(newOrderId);
        order.setFoodItemsList(orderFromFE.getFoodItemsList());
        order.setRestaurant(orderFromFE.getRestaurant());
        order.setUserDto(userDto);

        orderRepo.save(order);
        return OrderMapper.INSTANCE.toOrderDto(order);
    }

    private UserDto fetchUserDetailsById(Integer userId) {
        return restTemplate.getForObject("http://USERDETAILS/user/fetchByUserId/" + userId, UserDto.class);

    }
}
