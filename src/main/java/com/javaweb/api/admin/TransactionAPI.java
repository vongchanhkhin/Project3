package com.javaweb.api.admin;

import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "transactionAPIOfAdmin")
@RequestMapping("/api/transaction")
public class TransactionAPI {
    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            transactionService.addOrUpdateTransaction(transactionDTO);
            return ResponseEntity.ok(transactionDTO);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/note")
    public TransactionDTO getTransactionNote(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }
}
