package org.example;

import org.example.controller.TransactionController;
import org.example.model.Transaction;
import org.example.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTests {
    @Autowired
    private TransactionController transactionController;

    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        transactionService = mock(TransactionService.class);
        transactionController = new TransactionController(transactionService);
    }

    @Test
    public void testCreateTransaction() throws Exception {
        Transaction transaction = new Transaction("1", "DEPOSIT", 100.0);
        when(transactionService.createTransaction(any())).thenReturn(transaction);

        // Add your mockMvc test logic here
    }

    // Add more tests for other methods
}