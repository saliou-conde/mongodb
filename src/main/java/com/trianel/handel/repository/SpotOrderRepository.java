package com.trianel.handel.repository;

import com.trianel.handel.model.SpotOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpotOrderRepository extends MongoRepository<SpotOrder, String> {

    Optional<SpotOrder> findSpotOrderByOrderId(String orderId);
}
