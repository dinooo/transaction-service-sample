package com.num26.transactions.service;

import com.num26.transactions.domain.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction getTransactionById(long transaction_id) throws RuntimeException;
    Transaction addNewTransaction(long transaction_id, Transaction transactionToAdd);
    List<Long> getTransactionIdsByTransactionType(String transactionType);
    double getSumOfTransactionsByTransactionId(long transaction_id);
}
