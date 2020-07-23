package com.example.bank.controller;

import com.example.bank.entity.AccountEntity;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.model.Account;
import com.example.bank.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(AccountController.class)
@RunWith(SpringRunner.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountMapper accountMapper;

    private Account account;
    private AccountEntity accountEntity;
    private ObjectMapper objectMapper;

    @Before
    public void setUpTests() throws Exception {
        this.objectMapper = new ObjectMapper();
        account = objectMapper.readValue(this.setUpAccount(), Account.class);
        accountEntity = objectMapper.readValue(this.setUpAccount(), AccountEntity.class);
        accountEntity.setAccountId("0d3d84cd-6475-4635-bb0f-20cb8046f416");
    }

    @Test
    public void createAccount() throws Exception {
        Mockito.when(accountMapper.mapToAccountEntity(Mockito.any(Account.class))).thenReturn(this.accountEntity);
        Mockito.when(accountMapper.mapToAccount(Mockito.any(AccountEntity.class))).thenReturn(this.account);
        Mockito.when(accountService.createAccount(this.accountEntity)).thenReturn(this.accountEntity);
        this.mockMvc.perform(post("/account").content(this.objectMapper.writeValueAsString(this.account)).contentType("application/json")).andDo(print())
        .andExpect(status().isOk()).andExpect(content().json(setUpAccount()));
    }

    @Test
    public void updateAccountName() {

    }

    private String setUpAccount() {
        return "{\n" +
                "    \"username\":\"shmuhalb\",\n" +
                "    \"name\":\"Shmuel Halbfinger\",\n" +
                "    \"accountBalance\":150000\n" +
                "}";
    }

}
