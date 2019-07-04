package com.seb.account.component.service;

import com.lexicon.account.component.domain.Account;
import com.lexicon.account.component.entity.AccountEntity;
import com.seb.account.component.entity.AccountBalanceEntity;
import com.seb.account.component.entity.AccountTransactionEntity;
import com.seb.account.component.domain.AccountTransaction;
import com.seb.account.component.domain.CreateAccountTransactionRequest;
import com.seb.account.component.mapper.AccountBalanceMapper;
import com.seb.account.component.mapper.AccountTransactionMapper;
import com.seb.account.componment.dao.AccountBalanceDao;
import com.seb.account.component.domain.CreateAccountBalanceRequest;
import com.seb.account.component.domain.AccountBalance;
import com.seb.account.componment.dao.AccountDao;
import com.seb.account.componment.dao.AccountTransactionDao;
import com.lexicon.account.component.service.AccountComponentService;
import com.so4it.common.util.object.Required;
import com.so4it.gs.rpc.ServiceExport;

import java.util.Optional;

@ServiceExport({AccountComponentService.class})
public class AccountComponentServiceImpl implements AccountComponentService {

    private final AccountDao accountDao;

    public AccountComponentServiceImpl(AccountDao accountDao) {
        this.accountDao = Required.notNull(accountDao, "accountDao");
    }

    @Override
    public void createAccount(Account account) {
        AccountEntity  accountEntity = AccountEntity.builder().withSsn(account.getSsn()).withAmount(account.getAmount()).build();
        accountDao.insert(accountEntity);
    }
}