package com.num26.transactions.resources;

import com.num26.transactions.domain.Transaction;
import com.num26.transactions.service.TransactionServiceImpl;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Path("/transactionservice")
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class TransactionsResource {
    private final TransactionServiceImpl transactionService;

    @Inject
    public TransactionsResource(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @GET
    @Path("/transaction/{transaction_id}")
    public Transaction getTransaction(@PathParam("transaction_id") long transaction_id) {
        Transaction transaction = transactionService.getTransactionById(transaction_id);

        if (transaction == null) throw new WebApplicationException("Transaction not found", Response.Status.NOT_FOUND);

        return transaction;
    }

    @GET
    @Path("/sum/{transaction_id}")
    public Map<String, Double> getSummedAmountOfTransactionsById(@PathParam("transaction_id") long transaction_id) {
        double summedAmount = transactionService.getSumOfTransactionsByTransactionId(transaction_id);

        Map<String, Double> response = new HashMap<>();
        response.put("sum", summedAmount);

        return response;
    }

    @GET
    @Path("/types/{transactionType}")
    public List<Long> getTransactionIdsByTransactionType(@PathParam("transactionType") String transactionType) {
        return transactionService.getTransactionIdsByTransactionType(transactionType);
    }

    @PUT
    @Path("/transaction/{transaction_id}")
    public Map<String, String> addTransaction(@PathParam("transaction_id") long transaction_id,
                                              @Valid Transaction transactionToAdd) {
        transactionService.addNewTransaction(transaction_id, transactionToAdd);
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");

        return response;
    }
}
