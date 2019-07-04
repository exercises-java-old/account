package com.seb.account.component.client;

import com.lexicon.account.component.domain.Account;
import com.seb.account.component.domain.AccountBalance;
import com.seb.account.component.domain.AccountTransaction;
import com.seb.account.component.domain.CreateAccountBalanceRequest;
import com.seb.account.component.domain.CreateAccountTransactionRequest;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public interface AccountComponentClient {



    void createAccount(Account account);
}
