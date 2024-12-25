package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransactionService transactionService;

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

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long[] ids) {
        // Validate input
        if (ids == null || ids.length == 0) {
            return ResponseEntity.badRequest().body("No IDs provided");
        }
        try {
            customerService.deleteCustomer(ids);
            return ResponseEntity.noContent().build(); // HTTP 204: No Content
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateAssignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        try {
            customerService.updateAssignmentCustomer(assignmentCustomerDTO);
            return ResponseEntity.ok(assignmentCustomerDTO);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
