package com.trianel.handel.service.impl;

import com.trianel.handel.model.Customer;
import com.trianel.handel.model.SpotOrder;
import com.trianel.handel.model.dto.spotOrder.SpotOrderDto;
import com.trianel.handel.repository.CustomerRepository;
import com.trianel.handel.repository.SpotOrderRepository;
import com.trianel.handel.service.ITrianelService;
import com.trianel.handel.service.plausibility.alert.SpotOrderValidationResult;
import com.trianel.handel.service.plausibility.customer.CustomerValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trianel.handel.model.dto.spotOrder.SpotOrderDto.mapSpotOrderDtoToSpotOrder;
import static com.trianel.handel.model.dto.spotOrder.SpotOrderDto.mapSpotOrderToSpotOrderDto;
import static com.trianel.handel.service.plausibility.alert.SpotOrderValidationResult.SPOT_ORDER_VALID;
import static com.trianel.handel.service.plausibility.alert.SpotOrderValidator.isSpotOrderTimeout;
import static com.trianel.handel.service.plausibility.customer.CustomerValidationResult.VALID;
import static com.trianel.handel.service.plausibility.customer.CustomerValidator.customerExists;

@Service
@RequiredArgsConstructor
public class SpotOrderService implements ITrianelService<SpotOrderDto> {
    
    private final CustomerRepository customerRepository;
    private final SpotOrderRepository spotOrderRepository;
    
    @Override
    public SpotOrderDto addEntity(SpotOrderDto spotOrder) {
        String customerId = spotOrder.getCustomer().getCustomerId();

        Optional<Customer> optionalCustomer                 = customerRepository.findCustomerByCustomerId(customerId);
        CustomerValidationResult customerValidationResult   = customerExists().apply(optionalCustomer.orElse(null));
        if(customerValidationResult == VALID) {
            spotOrder.setCustomer(optionalCustomer.get());
            SpotOrder order = mapSpotOrderToSpotOrderDto(spotOrder);
            SpotOrderValidationResult spotOrderValidator = isSpotOrderTimeout().apply(order);
            if(spotOrderValidator == SPOT_ORDER_VALID) {
                SpotOrder insert = spotOrderRepository.save(order);
                return mapSpotOrderDtoToSpotOrder(insert);
            }
            throw new RuntimeException(spotOrderValidator.getDescription());
        }
        throw new RuntimeException(customerValidationResult.getDescription());
    }

    @Override
    public SpotOrderDto updateEntity(Object o, SpotOrderDto entity) {
        return null;
    }

    @Override
    public List<SpotOrderDto> getAllEntities() {
        return mapCustomerToCustomerDto(spotOrderRepository.findAll());
    }

    @Override
    public Boolean deleteEntity(Object o) {
        return null;
    }

    private List<SpotOrderDto> mapCustomerToCustomerDto(List<SpotOrder> spotOrders) {
        return spotOrders
                .stream()
                .map(SpotOrderDto::mapSpotOrderDtoToSpotOrder)
                .collect(Collectors.toList());
    }
}
