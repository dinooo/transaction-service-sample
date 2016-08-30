package com.num26.transactions.persistance;

import com.num26.transactions.domain.Transaction;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Named
public class TransactionServiceDao extends ConcurrentHashMap<Long, Transaction> {
}
