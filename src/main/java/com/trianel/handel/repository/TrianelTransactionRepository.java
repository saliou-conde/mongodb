package com.trianel.handel.repository;

import com.trianel.handel.model.TrianelTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrianelTransactionRepository extends MongoRepository<TrianelTransaction, String> {
}
