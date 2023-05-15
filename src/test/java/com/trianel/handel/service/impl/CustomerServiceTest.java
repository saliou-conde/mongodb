package com.trianel.handel.service.impl;

import com.trianel.handel.TrianelHandelApplication;
import com.trianel.handel.model.dto.customer.CustomerDto;
import com.trianel.handel.model.utility.Address;
import com.trianel.handel.model.utility.Gender;
import com.trianel.handel.service.ITrianelService;
import com.trianel.handel.service.exception.service.CustomerServiceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(classes = {TrianelHandelApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:application.properties")
class CustomerServiceTest {

    @Autowired
    private ITrianelService<CustomerDto> service;
    private CustomerDto customerDto;

    @BeforeAll
    void setUp() {
        Address address = Address
                .builder()
                .city("Merzenich")
                .country("Germany")
                .street("Mühlenstraße 26")
                .build();

        customerDto =  CustomerDto
                .builder()
                .address(address)
                .lastname("Saliou")
                .firstname("Condé")
                .gender(Gender.MALE)
                .email("saliou-conde@gmx.de")
                .password("19A12iou#")
                .build();
        return;
    }

    @Test
    void saveWith() {
        //Given
        customerDto.setEmail(generateMail("de"));
        String expectedMail = customerDto.getEmail();

        //When
        CustomerDto addEntity = service.addEntity(this.customerDto);

        //Then
        assertThat(addEntity).isNotNull();
        assertThat(expectedMail).isEqualTo(addEntity.getEmail());

        return;
    }

    @Test
    void saveWithAlreadyExistingMail() {
        //Given
        String expectedResult = "Custer Email already in use";

        //When
        try {
            service.addEntity(customerDto);
            fail();
        } catch (CustomerServiceException e) {
            assertEquals(expectedResult, e.getMessage());
        }
        return;
    }

    @Test
    void getCustomers() {
        //when
        List<CustomerDto> entities = service.getAllEntities();

        //Then
        assertThat(entities).isNotNull();
    }

    private String generateMail(String domain) {
        StringBuilder emailAddress = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        while (emailAddress.length() < 5) {
            int character = (int) (Math.random() * 26);
            emailAddress.append(alphabet.substring(character, character + 1));
            emailAddress.append(Integer.valueOf((int) (Math.random() * 99)).toString());
            emailAddress.append("@gmail." + domain);
        }
        return emailAddress.toString();
    }
}