package com.lexicon.account.component.service;

import com.lexicon.account.component.domain.Account;
import com.seb.account.component.domain.AccountTransaction;
import com.seb.account.component.domain.CreateAccountBalanceRequest;
import com.seb.account.component.domain.AccountBalance;
import com.seb.account.component.domain.CreateAccountTransactionRequest;
import com.so4it.gs.rpc.Routing;

import java.util.Optional;

public interface AccountComponentService {

    String DEFAULT_BEAN_NAME = "accountComponentService";

    //account.hashCode() % numberOfPartitions
    void createAccount(@Routing("getSsn") Account account);




}
