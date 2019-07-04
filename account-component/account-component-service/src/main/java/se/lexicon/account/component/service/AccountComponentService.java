package se.lexicon.account.component.service;

import se.lexicon.account.component.domain.Account;
import se.lexicon.account.component.domain.AccountTransaction;
import se.lexicon.account.component.domain.CreateAccountBalanceRequest;
import se.lexicon.account.component.domain.AccountBalance;
import se.lexicon.account.component.domain.CreateAccountTransactionRequest;
import com.so4it.gs.rpc.Routing;

public interface AccountComponentService {

    String DEFAULT_BEAN_NAME = "accountComponentService";

    //account.hashCode() % numberOfPartitions
    void createAccount(@Routing("getSsn") Account account);




}
