package com.trianel.handel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class SpotOrder {

    @Id
    @GeneratedValue
    private Long orderId;
    private Double quantity;
    private Date timestamp;

    @ManyToOne(
            cascade = CascadeType.REMOVE
    )
    @JoinColumn(
            name = "customerId",
            referencedColumnName = "customerId"
    )
    @OnDelete(
            action = OnDeleteAction.CASCADE
    )
    private Customer customer;
}
