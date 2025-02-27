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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void testCreateTransaction() throws Exception {
        Transaction transaction = new Transaction("1", "DEPOSIT", 100.0);
        when(transactionService.createTransaction(any())).thenReturn(transaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\", \"type\": \"DEPOSIT\", \"amount\": 100.0}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(transactionService, times(1)).createTransaction(any());
    }

    @Test
    public void testDeleteTransaction() throws Exception {
        String id = "1";
        doNothing().when(transactionService).deleteTransaction(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/transactions/{id}", id))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(transactionService, times(1)).deleteTransaction(id);
    }

    @Test
    public void testModifyTransaction() throws Exception {
        String id = "1";
        Transaction transaction = new Transaction(id, "DEPOSIT", 200.0);
        // 使用匹配器来匹配两个参数
        when(transactionService.modifyTransaction(anyString(), any(Transaction.class))).thenReturn(transaction);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/transactions/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\", \"type\": \"DEPOSIT\", \"amount\": 200.0}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 验证方法调用
        verify(transactionService, times(1)).modifyTransaction(anyString(), any(Transaction.class));
    }

    @Test
    public void testListTransactions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(transactionService, times(1)).listTransactions();
    }
}