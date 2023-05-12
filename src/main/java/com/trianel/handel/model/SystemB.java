package com.trianel.handel.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class SystemB {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String systemBID;
    private String name;
    private List<SpotOrder> spotOrders;
}
