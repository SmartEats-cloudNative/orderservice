package com.example.orderservice.service;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderFromFE;
import com.example.orderservice.dto.UserDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repo.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    @Mock
    private OrderRepo orderRepo;

    @Mock
    private SequenceGenerator sequenceGenerator;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrderInDb_shouldSaveOrderAndReturnOrderDTO() {
        // Arrange
        OrderFromFE orderDetails = new OrderFromFE();
        Integer newOrderId = 1;
        UserDto userDTO = new UserDto();
        Order orderToBeSaved = new Order();
        orderToBeSaved.setOrderId(newOrderId);
        orderToBeSaved.setFoodItemsList(orderDetails.getFoodItemsList());
        orderToBeSaved.setRestaurant(orderDetails.getRestaurant());
        orderToBeSaved.setUserDto(userDTO);
        OrderDto orderDTOExpect = OrderMapper.INSTANCE.toOrderDto(orderToBeSaved);
        Order orderExpect = OrderMapper.INSTANCE.toOrder(orderDTOExpect);
        OrderDto orderDTOExpected = OrderMapper.INSTANCE.toOrderDto(orderToBeSaved);
        when(sequenceGenerator.generateNextOrderId()).thenReturn(newOrderId);
        when(restTemplate.getForObject(anyString(), eq(UserDto.class))).thenReturn(userDTO);
        when(orderRepo.save(orderToBeSaved)).thenReturn(orderToBeSaved);

        // Act
        OrderDto orderDTOActual = orderService.saveOrderToDb(orderDetails);

        // Assert
        verify(sequenceGenerator, times(1)).generateNextOrderId();
        assertDoesNotThrow(() -> orderService.saveOrderToDb(orderDetails));
    }
}
