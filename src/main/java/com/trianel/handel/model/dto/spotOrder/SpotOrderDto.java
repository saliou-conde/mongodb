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

    private Long orderId;
    private Double quantity;
    private LocalDateTime timestamp;
    private Customer customer;

    public static SpotOrderDto mapSpotOrderDtoToSpotOrder(SpotOrder spotOrder) {
        return SpotOrderDto.builder()
                .orderId(spotOrder.getOrderId())
                .quantity(spotOrder.getQuantity())
                .timestamp(LocalDateTime.now())
                .customer(spotOrder.getCustomer())
                .build();
    }


    public static SpotOrder mapSpotOrderToSpotOrderDto(SpotOrderDto spotOrder) {
        boolean isTimestampNull = spotOrder.timestamp == null;
        return SpotOrder.builder()
                .orderId(spotOrder.getOrderId())
                .quantity(spotOrder.getQuantity())
                .timestamp(isTimestampNull ? LocalDateTime.now() : spotOrder.timestamp)
                .customer(spotOrder.getCustomer())
                .build();
    }
}
