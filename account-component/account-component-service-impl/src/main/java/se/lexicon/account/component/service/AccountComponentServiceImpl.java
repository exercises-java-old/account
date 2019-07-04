package se.lexicon.account.component.service;

import se.lexicon.account.component.domain.Account;
import se.lexicon.account.component.entity.AccountEntity;
import se.lexicon.account.component.entity.AccountTransactionEntity;
import se.lexicon.account.component.domain.AccountTransaction;
import se.lexicon.account.component.domain.CreateAccountTransactionRequest;
import se.lexicon.account.component.mapper.AccountBalanceMapper;
import se.lexicon.account.component.mapper.AccountTransactionMapper;
import se.lexicon.account.component.domain.CreateAccountBalanceRequest;
import se.lexicon.account.component.domain.AccountBalance;
import se.lexicon.account.componment.dao.AccountDao;
import com.so4it.common.util.object.Required;
import com.so4it.gs.rpc.ServiceExport;

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