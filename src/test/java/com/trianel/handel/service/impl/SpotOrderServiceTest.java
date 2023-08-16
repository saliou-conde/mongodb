package com.trianel.handel.service.impl;

import com.trianel.handel.TrianelHandelApplication;
import com.trianel.handel.model.Customer;
import com.trianel.handel.model.dto.spotOrder.SpotOrderDto;
import com.trianel.handel.service.ITrianelService;
import com.trianel.handel.service.exception.service.CustomerServiceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(classes = {TrianelHandelApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpotOrderServiceTest {

    @Autowired
    private ITrianelService<SpotOrderDto> service;
    private SpotOrderDto spotOrderDto;
    private String customerId = "40ff08a1-e89c-4edc-a2aa-a7a926f9948c";

    @BeforeAll
    void setUp() {
        Customer customer = Customer.builder()
                .customerId(customerId)
                .build();

        spotOrderDto = SpotOrderDto
                .builder()
                .quantity(200.0 + Math.random()*100 + 2*Math.random()*100)
                .customer(customer)
                .createdAt(LocalDateTime.now().plusDays(3))
                .build();

        return;
    }

    @Test
    void addEntityWithAlreadyExistingEmail() {
        //Given
        String expectedResult = "Custer Email already in use";
        String customerId = this.customerId;

        //When
        try {
            service.addEntity(this.spotOrderDto);
            fail();
        } catch (CustomerServiceException e) {
            assertThat(e.getMessage()).isEqualTo(expectedResult);
        }

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