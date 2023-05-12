package com.trianel.handel.repository;

import com.trianel.handel.model.SpotOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpotOrderRepository extends MongoRepository<SpotOrder, String> {
}
