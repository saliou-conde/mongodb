package com.trianel.handel.service.impl;

import com.trianel.handel.TrianelHandelApplication;
import com.trianel.handel.model.Customer;
import com.trianel.handel.model.dto.spotOrder.SpotOrderDto;
import com.trianel.handel.service.ITrianelService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {TrianelHandelApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpotOrderServiceTest {

    @Autowired
    private ITrianelService<SpotOrderDto> service;
    private SpotOrderDto spotOrderDto;
    private String customerId = "9b5bec9e-2789-4e45-ae2f-dc72a69ef596";

    @BeforeAll
    void setUp() {
        Customer customer = Customer.builder()
                .customerId(customerId)
                .build();

        spotOrderDto = SpotOrderDto
                .builder()
                .quantity(200.0)
                .customer(customer)
                .build();

        return;
    }

    @Test
    void addEntity() {
        //Given
        String customerId = this.customerId;

        //When
        SpotOrderDto spotOrderDto = service.addEntity(this.spotOrderDto);

        //Then
        assertThat(customerId).isEqualTo(spotOrderDto.getCustomer().getCustomerId());
    }

    @Test
    void updateEntity() {
    }

    @Test
    void getAllEntities() {
    }

    @Test
    void deleteEntity() {
    }
}