package com.javaweb.api.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public ResponseEntity<?> addOrUpdateCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            customerService.addOrUpdateCustomer(customerDTO);
            return ResponseEntity.ok(customerDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO getStaffs(@PathVariable Long id) {
        ResponseDTO result = customerService.getAllStaffInAssignmentCustomer(id);
        return result;
    }

}
