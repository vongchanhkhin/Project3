package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface ICustomerService {
    List<CustomerSearchResponse> getAllCustomers(CustomerSearchRequest customerSearchRequest);
    int countTotalItems();
    ResponseDTO getAllStaffInAssignmentCustomer(Long customerId);
    void addOrUpdateCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Long customerId);

}
