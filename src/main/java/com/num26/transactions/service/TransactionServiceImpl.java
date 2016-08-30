package com.num26.transactions.service;

import com.num26.transactions.domain.Transaction;
import com.num26.transactions.persistance.TransactionServiceDao;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class TransactionServiceImpl implements TransactionService {
    private final TransactionServiceDao transactionServiceDao;

    @Inject
    public TransactionServiceImpl(TransactionServiceDao transactionServiceDao) {
        this.transactionServiceDao = transactionServiceDao;
    }

    @Override
    public Transaction getTransactionById(long transaction_id) {
        return transactionServiceDao.get(transaction_id);
    }

    @Override
    public List<Long> getTransactionIdsByTransactionType(String transactionType) {
        return transactionServiceDao
                .entrySet()
                .stream()
                .filter(t -> t.getValue().getType().equals(transactionType))
                .map(t -> t.getValue().getTransaction_id())
                .collect(Collectors.toList());
    }

    @Override
    public double getSumOfTransactionsByTransactionId(long transaction_id) {
        return transactionServiceDao
                .entrySet()
                .stream()
                .filter(t -> t.getValue().getTransaction_id().equals(transaction_id) ||
                        (t.getValue().getParent_id() != null && t.getValue().getParent_id().equals(transaction_id)))
                .mapToDouble(t -> t.getValue().getAmount()).sum();
    }

    @Override
    public Transaction addNewTransaction(long transaction_id, @NotNull Transaction transactionToAdd) {
        if (transactionToAdd.getParent_id() != null && getTransactionById(transactionToAdd.getParent_id()) == null) {
            throw new WebApplicationException("Parent transaction doesn't exist", Response.Status.BAD_REQUEST);
        }

        transactionToAdd.setTransaction_id(transaction_id);
        transactionServiceDao.put(transaction_id, transactionToAdd);

        return transactionToAdd;
    }
}
