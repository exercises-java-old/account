package com.seb.account.api.client;


import com.seb.account.component.domain.AccountBalance;
import com.seb.account.component.domain.AccountTransaction;
import com.seb.account.component.domain.CreateAccountBalanceRequest;
import com.seb.account.component.domain.CreateAccountTransactionRequest;

public interface AccountApiClient {
    String DEFAULT_API_BEAN_NAME = "accountApiClient";

    AccountBalance createAccountBalance(CreateAccountBalanceRequest request);
    AccountTransaction createAccountTransaction(CreateAccountTransactionRequest request);

}
