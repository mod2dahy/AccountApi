package com.capgemini.accountapi.controller;

import com.capgemini.accountapi.exception.AccountNotFoundException;
import com.capgemini.accountapi.model.Account;
import com.capgemini.accountapi.repository.AccountRepository;
import com.capgemini.accountapi.services.AccountService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @Mock AccountRepository accountRepository;	
    
    @InjectMocks
    private AccountController accountController;
    @Test
    void checkRepositoryFilled()
    {
    	accountRepository=new AccountRepository();
    	assertEquals(accountRepository.getTotalAccounts(), 3);
    }
    
    @Test
    void getAccount_ReturnsAccepted() throws Exception {
        when(accountService.getAccountInformation(anyString())).thenReturn(new Account("3", "Jane", "Doe", true));

        mockMvc.perform(get("/api/accounts")
                .param("customerId", "100001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
     
    }

    @Test
    void createAccount_ReturnsOK() throws Exception {
    	
        when(accountService.openNewAccount(anyString(), anyDouble())).thenReturn(new Account("1", "John", "Doe", true));
        
        mockMvc.perform(post("/api/accounts")
                .param("customerId", "100001")
                .param("initialCredit", "100.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerID").value("100001"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    void createAccount_ReturnsNotFound() throws Exception {
    	when(accountService.openNewAccount(anyString(), anyDouble()))
        .thenThrow(new AccountNotFoundException("2"));

mockMvc.perform(post("/api/accounts")
        .param("customerId", "2")
        .param("initialCredit", "50.0")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    }


    @Test
    void getAccount_ReturnsNotFound() throws Exception {
        when(accountService.getAccountInformation(anyString())).thenReturn(null);

        mockMvc.perform(get("/api/accounts")
                .param("customerId", "1000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

  
}
