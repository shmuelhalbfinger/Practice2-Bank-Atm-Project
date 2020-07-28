package com.example.bank.controller;

import com.example.bank.entity.AccountEntity;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.model.Account;
import com.example.bank.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    private String editName;

    @Before
    public void setUpTests() throws Exception {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void createAccount() throws Exception {

    }

    @Test
    public void createAccountBad() throws Exception {

    }

    @Test
    public void updateAccountName() throws Exception {
    }

    private void setUpAccount() throws Exception {
        String json = "{\n" +
                "    \"username\":\"shmuhalb\",\n" +
                "    \"name\":\"Shmuel Halbfinger\",\n" +
                "    \"accountBalance\":150000\n" +
                "}";

        account = objectMapper.readValue(json, Account.class);
        accountEntity = objectMapper.readValue(json, AccountEntity.class);
        accountEntity.setAccountId("0d3d84cd-6475-4635-bb0f-20cb8046f416");
    }

    private void setUpAccountBad() throws Exception {
        String json = "{\n" +
                "    \"username\":\"shmuhalb\",\n" +
                "    \"name\":\"Shmuel Halbfinger\",\n" +
                "    \"accountBalance\":\"Hello\"\n" +
                "}";

        account = objectMapper.readValue(json, Account.class);
        accountEntity = objectMapper.readValue(json, AccountEntity.class);
        accountEntity.setAccountId("0d3d84cd-6475-4635-bb0f-20cb8046f416");
    }

}
