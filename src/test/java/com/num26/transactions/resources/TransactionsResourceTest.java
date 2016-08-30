package com.num26.transactions.resources;

import com.num26.transactions.ResourceTest;
import com.num26.transactions.domain.Transaction;
import com.num26.transactions.service.TransactionServiceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionsResourceTest extends ResourceTest {
    private final TransactionServiceImpl transactionService = mock(TransactionServiceImpl.class);
    private Transaction transaction = new Transaction(1L, 100d, "Cars", null);

    @Override
    protected void setUpResources(ResourceConfig application) throws Exception {
        when(transactionService.getTransactionById(1L)).thenReturn(transaction);
        application.registerInstances(new TransactionsResource(transactionService));
    }

    @Test
    public void shouldGetTransactionById() {
        Transaction response = getJersey().target("transactionservice/transaction/1").request().get(Transaction.class);
        assertNotNull(response);
        assertTrue(response.getTransaction_id() == 1L);
    }

    @Test
    public void shouldGetNotFoundIfTransactionDoesntExist() {
        Response response = getJersey().target("transactionservice/transaction/3").request().get();
        assertEquals(404, response.getStatus());
    }

    @Test
    public void shouldAddNewTransaction() {
        Map response = getJersey().target("transactionservice/transaction/3")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(transaction), HashMap.class);

        assertEquals("ok", response.get("status"));
    }

    @Test
    public void shouldReturnBadRequestIfAmountIsNotSpecified() {
        transaction.setAmount(null);
        Response response = getJersey().target("transactionservice/transaction/3")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(transaction), Response.class);

        assertEquals(400, response.getStatus());
    }
}
