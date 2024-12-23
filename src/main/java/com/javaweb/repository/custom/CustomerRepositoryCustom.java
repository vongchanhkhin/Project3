package com.javaweb.repository.custom;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> findAll(CustomerSearchBuilder customerSearchBuilder);
    int countTotalItems();
}
