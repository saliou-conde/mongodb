package com.trianel.handel.service;

import com.trianel.handel.model.dto.customer.LoginDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITrianelService<T> {

    T addEntity(T entity);

    T updateEntity(Object o, T entity);

    List<T> getAllEntities();

    Boolean deleteEntity(Object o);

    default T authenticate(LoginDto login) {
        return null;
    }

    default Page<T> searchCustomer(String name, Integer min, Integer max, String city, Pageable pageable) {
        return null;
    }
}
