package com.trianel.handel.service.impl;

import com.trianel.handel.model.Customer;
import com.trianel.handel.model.SpotOrder;
import com.trianel.handel.model.dto.spotOrder.SpotOrderDto;
import com.trianel.handel.repository.CustomerRepository;
import com.trianel.handel.repository.SpotOrderRepository;
import com.trianel.handel.service.ITrianelService;
import com.trianel.handel.service.plausibility.CustomerValidationResult;
import com.trianel.handel.service.plausibility.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.trianel.handel.service.plausibility.CustomerValidationResult.*;

@Service
@RequiredArgsConstructor
public class SpotOrderService implements ITrianelService<SpotOrderDto> {
    
    private final CustomerRepository customerRepository;
    private final SpotOrderRepository spotOrderRepository;
    
    @Override
    public SpotOrderDto addEntity(SpotOrderDto spotOrder) {
        String customerId = spotOrder.getCustomer().getCustomerId();

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByCustomerId(customerId);
        CustomerValidationResult apply      = CustomerValidator.customerExists().apply(optionalCustomer.orElse(null));
        if(apply == VALID) {
            spotOrder.setCustomer(optionalCustomer.get());
            SpotOrder insert = spotOrderRepository.save(SpotOrderDto.mapSpotOrderToSpotOrderDto(spotOrder));
            return SpotOrderDto.mapSpotOrderDtoToSpotOrder(insert);
        }
        throw new RuntimeException(apply.getDescription());
    }

    @Override
    public SpotOrderDto updateEntity(Object o, SpotOrderDto entity) {
        return null;
    }

    @Override
    public List<SpotOrderDto> getAllEntities() {
        return null;
    }

    @Override
    public Boolean deleteEntity(Object o) {
        return null;
    }
}
