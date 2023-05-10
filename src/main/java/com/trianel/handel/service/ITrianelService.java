package com.trianel.handel.service;

import com.trianel.handel.model.dto.LoginDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITrianelService<T> {

    T addEntity(T entity);

    T updateEntity(Object o, T entity);

    List<T> getAllEntities();

    T authenticate(LoginDto login);

    Page<T> searchCustomer(String name, Integer minAge, Integer maxAge, String city, Pageable pageable);

    Boolean deleteEntity(Object o);
}
