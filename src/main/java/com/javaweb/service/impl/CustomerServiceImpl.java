package com.javaweb.service.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public List<CustomerSearchResponse> getAllCustomers(CustomerSearchRequest customerSearchRequest) {
        CustomerSearchBuilder customerSearchBuilder = customerConverter.toCustomerSearchBuilder(customerSearchRequest);
        List<CustomerEntity> customerEntities = customerRepository.findAll(customerSearchBuilder);
        List<CustomerSearchResponse> results = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntities) {
            results.add(customerConverter.toCustomerSearchResponse(customerEntity));
        }

        return results;
    }

    @Override
    public int countTotalItems() {
        return customerRepository.countTotalItems();
    }

    @Override
    public ResponseDTO getAllStaffInAssignmentCustomer(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId).get();
        List<UserEntity> staff = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignment = customer.getStaff();

        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();

        for (UserEntity user : staff) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(user.getId());
            staffResponseDTO.setFullName(user.getFullName());

            if (staffAssignment.contains(user)) {
                staffResponseDTO.setChecked("checked");
            }
            else staffResponseDTO.setChecked("");

            staffResponseDTOS.add(staffResponseDTO);
        }

        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("Success");

        return responseDTO;
    }

    @Override
    public void addOrUpdateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.toCustomerEntity(customerDTO);
        customerRepository.save(customerEntity);
        return;
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();
        CustomerDTO customerDTO = customerConverter.toCustomerDTO(customerEntity);

        return customerDTO;
    }
}
