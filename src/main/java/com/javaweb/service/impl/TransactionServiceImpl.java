package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionConverter transactionConverter;

    @Override
    public void addOrUpdateTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = transactionConverter.toTransactionEntity(transactionDTO);

        transactionRepository.save(transactionEntity);
    }

    @Override
    public List<TransactionDTO> getTransactionsByCustomerId(Long customerId) {
        List<TransactionEntity> transactionEntities = transactionRepository.findByCustomerId(customerId);
        List<TransactionDTO> results = new ArrayList<>();

        for (TransactionEntity transactionEntity : transactionEntities) {
            results.add(transactionConverter.toTransactionDTO(transactionEntity));
        }

        return results;
    }

    @Override
    public TransactionDTO getTransactionById(Long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).get();
        TransactionDTO transactionDTO = transactionConverter.toTransactionDTO(transactionEntity);

        return transactionDTO;
    }
}
