package com.trianel.handel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class SpotOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;
    @Column(nullable = false)
    private Double quantity;
    @Column(nullable = false)
    private LocalDateTime createdAt;

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
