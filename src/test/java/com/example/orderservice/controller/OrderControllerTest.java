package com.example.orderservice.controller;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderFromFE;
import com.example.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrder_shouldReturnCreatedStatus() {
        // Arrange
        OrderFromFE orderDetails = new OrderFromFE();
        OrderDto orderSavedInDB = new OrderDto();
        when(orderService.saveOrderToDb(orderDetails)).thenReturn(orderSavedInDB);

        // Act
        ResponseEntity<OrderDto> response = orderController.saveOrderToDb(orderDetails);

        // Assert
        verify(orderService, times(1)).saveOrderToDb(orderDetails);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderSavedInDB, response.getBody());
    }
}
