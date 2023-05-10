package com.trianel.handel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    private Double quantity;
    private Double price;

    @ManyToOne(
            cascade = CascadeType.REMOVE
    )
    @JoinColumn(
            name = "orderId",
            referencedColumnName = "orderId"
    )
    @OnDelete(
            action = OnDeleteAction.CASCADE
    )
    private SpotOrder spotOrder;

    @OneToOne(
            cascade = CascadeType.REMOVE
    )
    @JoinColumn(name = "customerId",
            referencedColumnName = "customerId"
    )
    @OnDelete(
            action = OnDeleteAction.CASCADE
    )
    private Customer customer;
}
