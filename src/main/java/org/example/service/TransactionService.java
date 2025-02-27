package org.example.service;

import org.example.exception.TransactionNotFoundException;
import org.example.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransactionService {
    private final ConcurrentHashMap<String, Transaction> transactions = new ConcurrentHashMap<>();

    public Transaction createTransaction(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
        return transaction;
    }

    public void deleteTransaction(String id) {
        if (transactions.remove(id) == null) {
            throw new TransactionNotFoundException("Transaction not found");
        }
    }

    public Transaction modifyTransaction(String id, Transaction transaction) {
        if (!transactions.containsKey(id)) {
            throw new TransactionNotFoundException("Transaction not found");
        }
        transaction.setId(id);
        transactions.put(id, transaction);
        return transaction;
    }

    public List<Transaction> listTransactions() {
        return new ArrayList<>(transactions.values());
    }
}