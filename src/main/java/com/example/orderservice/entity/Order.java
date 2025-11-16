package com.example.orderservice.entity;

import com.example.orderservice.dto.FoodDto;
import com.example.orderservice.dto.Restaurant;
import com.example.orderservice.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {

    private Integer orderId;
    private List<FoodDto> foodItemsList;
    private UserDto userDto;
    private Restaurant restaurant;
}
