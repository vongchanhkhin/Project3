package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    void addOrUpdateTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> getTransactionsByCustomerId(Long customerId);
    TransactionDTO getTransactionById(Long id);
}
