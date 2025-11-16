package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {
    private int id;
    private String itemName;
    private String itemDescription;
    private boolean isVegetarian;
    private BigDecimal price;
    private Integer restaurantId;
    private Integer quantity;
}
