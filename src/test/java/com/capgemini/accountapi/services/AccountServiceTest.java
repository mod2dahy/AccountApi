//package com.capgemini.accountapi.services;
//
//import com.capgemini.accountapi.exception.AccountNotFoundException;
//import com.capgemini.accountapi.model.Account;
//import com.capgemini.accountapi.repository.AccountRepository;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class AccountServiceTest {
//
//    @Mock
//    private TransactionService transactionService;
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @InjectMocks
//    private AccountService accountService;
//    
//   
//
//    @Test
//    void openNewAccount_WithInitialCredit() {
//    	
//        when(transactionService.createTransaction(anyString(), anyDouble())).thenReturn(null);
//
//        Account result = accountService.openNewAccount("100001", 100.0);
//
//        verify(transactionService, times(1)).createTransaction(eq("1"), eq(100.0));
//        verify(transactionService, times(1)).createTransaction(anyString(), anyDouble());
//
//        // Add assertions based on your actual business logic and expected results
//    }
//
//    @Test
//    void openNewAccount_WithoutInitialCredit() {
//    	try
//    	{
//    	accountService.openNewAccount("2", 0);
//    	
//        
//        // 
//    	}
//    	catch (Exception e) {
//    		assertThatExceptionOfType(AccountNotFoundException.class);
//		}
//    }
//
//    @Test
//    void getAccountInformation_ExistingAccount() {
//        when(accountRepository.findByCustomerId("100002"));
//
//        Account result = accountService.getAccountInformation("100002");
//
//        verify(accountRepository, times(1)).findByCustomerId("3");
//        verify(accountRepository, times(1)).findByCustomerId(anyString());
//
//        // Add assertions based on your actual business logic and expected results
//    }
//
//    @Test
//    void getAccountInformation_NonExistingAccount() {
//    	when(accountRepository.findByCustomerId("4")).thenReturn(Optional.empty());
//
//        Account result = accountService.getAccountInformation("4");
//
//        verify(accountRepository, times(1)).findByCustomerId("4");
//        verify(accountRepository, times(1)).findByCustomerId(anyString());
//
//        // Add assertions based on your actual business logic and expected results
//    }
//}
