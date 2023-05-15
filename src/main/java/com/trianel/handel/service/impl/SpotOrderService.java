package com.trianel.handel.service.impl;

import com.trianel.handel.model.Customer;
import com.trianel.handel.model.SpotOrder;
import com.trianel.handel.model.dto.spotOrder.SpotOrderDto;
import com.trianel.handel.repository.CustomerRepository;
import com.trianel.handel.repository.SpotOrderRepository;
import com.trianel.handel.service.ITrianelService;
import com.trianel.handel.service.exception.service.CustomerServiceException;
import com.trianel.handel.service.exception.service.SpotOrderServiceException;
import com.trianel.handel.service.plausibility.order.SpotOrderValidation;
import com.trianel.handel.service.plausibility.customer.CustomerValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trianel.handel.model.dto.customer.CustomerDto.sanitize;
import static com.trianel.handel.model.dto.spotOrder.SpotOrderDto.mapSpotOrderDtoToSpotOrder;
import static com.trianel.handel.model.dto.spotOrder.SpotOrderDto.mapSpotOrderToSpotOrderDto;
import static com.trianel.handel.service.plausibility.order.SpotOrderValidation.SPOT_ORDER_VALID;
import static com.trianel.handel.service.plausibility.order.SpotOrderValidator.isSpotOrderTimeout;
import static com.trianel.handel.service.plausibility.customer.CustomerValidation.VALID;
import static com.trianel.handel.service.plausibility.customer.CustomerValidator.customerExists;

@Service
@RequiredArgsConstructor
public class SpotOrderService implements ITrianelService<SpotOrderDto> {
    
    private final CustomerRepository customerRepository;
    private final SpotOrderRepository spotOrderRepository;
    
    @Override
    public SpotOrderDto addEntity(SpotOrderDto spotOrder){
        String customerId = spotOrder.getCustomer().getCustomerId();

        Optional<Customer> optionalCustomer     = customerRepository.findCustomerByCustomerId(customerId);
        CustomerValidation customerValidation   = customerExists().apply(optionalCustomer.orElse(null));
        if(customerValidation == VALID) {
            Customer customer = sanitize(optionalCustomer.get());
            spotOrder.setCustomer(customer);
            SpotOrder order                         = mapSpotOrderToSpotOrderDto(spotOrder);
            SpotOrderValidation orderValidation     = isSpotOrderTimeout().apply(order);
            if(orderValidation == SPOT_ORDER_VALID) {
                SpotOrder insert = spotOrderRepository.save(order);
                return mapSpotOrderDtoToSpotOrder(insert);
            }
            throw new SpotOrderServiceException(orderValidation.getDescription());
        }
        throw new CustomerServiceException(customerValidation.getDescription());
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
