package com.trianel.handel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class SpotOrder {

    @Field("orderId")
    private Long orderId;
    @Column(nullable = false)
    private Double quantity;
    @Column(nullable = false)
    private LocalDateTime timestamp;

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
