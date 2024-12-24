package com.javaweb.converter;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.utils.MapUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerSearchBuilder toCustomerSearchBuilder(CustomerSearchRequest customerSearchRequest) {
        CustomerSearchBuilder customerSearchBuilder = new CustomerSearchBuilder.Builder()
                .setName(MapUtils.getObject(customerSearchRequest.getName(), String.class))
                .setPhone(MapUtils.getObject(customerSearchRequest.getPhone(), String.class))
                .setEmail(MapUtils.getObject(customerSearchRequest.getEmail(), String.class))
                .setStaffId(MapUtils.getObject(customerSearchRequest.getStaffId(), Long.class))
                .build();

        return customerSearchBuilder;
    }

    public CustomerSearchResponse toCustomerSearchResponse(CustomerEntity customerEntity) {
        CustomerSearchResponse customerSearchResponse = modelMapper.map(customerEntity, CustomerSearchResponse.class);

        return customerSearchResponse;
    }

    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);

        return customerEntity;
    }

    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        customerDTO.setManagementStaff(customerEntity.getCreatedBy());

        return customerDTO;
    }
}
