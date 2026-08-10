package com.scoops.brainfreeze.controller;

import com.scoops.brainfreeze.model.Order;
import com.scoops.brainfreeze.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    private Long orderId;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();

        Order order = new Order();
        order.setUserId(1L); // Belongs to user 1
        order.setFlavorId(1L);
        order.setQuantity(2);
        order.setNotes("Extra chocolate chips please");
        order.setTotalPrice(280.0);
        order = orderRepository.save(order);
        orderId = order.getId();
    }

    @Test
    void getOrder_shouldReturnOrderEvenWithoutAuthentication() throws Exception {
        // This test documents the vulnerable behavior (A01 - Broken Access Control)
        // In a secure application this should require authentication + ownership check
        mockMvc.perform(get("/api/orders/" + orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.notes").value("Extra chocolate chips please"));
    }
}
