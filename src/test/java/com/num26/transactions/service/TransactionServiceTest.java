package com.num26.transactions.service;

import com.num26.transactions.domain.Transaction;
import com.num26.transactions.persistance.TransactionServiceDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ws.rs.WebApplicationException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TransactionServiceTest {
    private final static String TRANSACTION_TYPE = "Bicycle";

    private Transaction transaction = new Transaction(1L, 200d, TRANSACTION_TYPE, null);
    private Transaction transaction2 = new Transaction(2L, 300d, TRANSACTION_TYPE, 1L);
    private TransactionServiceImpl transactionService;
    private final TransactionServiceDao transactionServiceDao = new TransactionServiceDao();

    @Before
    public void setup() {
        transactionServiceDao.put(1L, transaction);
        transactionServiceDao.put(2L, transaction2);

        transactionService = new TransactionServiceImpl(transactionServiceDao);
    }

    @Test
    public void shouldReturnOneByTransactionId() {
        assertEquals(transaction, transactionService.getTransactionById(1L));
    }

    @Test
    public void shouldAddNewTransaction() {
        Transaction newTransaction = new Transaction(5000d, TRANSACTION_TYPE, null);
        transactionService.addNewTransaction(90L, newTransaction);

        assertNotNull(transactionService.getTransactionById(90L));
    }

    @Test(expected = WebApplicationException.class)
    public void shouldReturnBadRequestIfParentTransactionDoesntExist() {
        Transaction newTransaction = new Transaction(5000d, TRANSACTION_TYPE, 99L);
        transactionService.addNewTransaction(90L, newTransaction);
    }

    @Test
    public void shouldSumAllTransactionsById() {
        assertTrue(500d == transactionService.getSumOfTransactionsByTransactionId(1L));
        assertTrue(300d == transactionService.getSumOfTransactionsByTransactionId(2L));
    }

    @Test
    public void shouldGetListOfTransactionIdsByTransactionType() {
        List<Long> transactionIds = transactionService.getTransactionIdsByTransactionType(TRANSACTION_TYPE);

        assertTrue(transactionIds.contains(1L));
        assertTrue(transactionIds.contains(2L));
    }
}
