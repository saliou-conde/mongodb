package com.trianel.handel.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class TrianelTransaction {

    @Field("transactionId")
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
