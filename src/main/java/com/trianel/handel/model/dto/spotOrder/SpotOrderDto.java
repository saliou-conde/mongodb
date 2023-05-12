package com.trianel.handel.model.dto.spotOrder;

import com.trianel.handel.model.Customer;
import com.trianel.handel.model.SpotOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotOrderDto {

    private String orderId;
    private Double quantity;
    private LocalDateTime createdAt;
    private Customer customer;

    public static SpotOrderDto mapSpotOrderDtoToSpotOrder(SpotOrder spotOrder) {
        return SpotOrderDto.builder()
                .orderId(spotOrder.getOrderId())
                .quantity(spotOrder.getQuantity())
                .createdAt(LocalDateTime.now())
                .customer(spotOrder.getCustomer())
                .build();
    }


    public static SpotOrder mapSpotOrderToSpotOrderDto(SpotOrderDto spotOrder) {
        boolean isTimestampNull = spotOrder.createdAt == null;
        return SpotOrder.builder()
                .orderId(spotOrder.getOrderId())
                .quantity(spotOrder.getQuantity())
                .createdAt(isTimestampNull ? LocalDateTime.now() : spotOrder.createdAt)
                .customer(spotOrder.getCustomer())
                .build();
    }
}
