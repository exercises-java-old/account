package com.seb.account.componment.dao;

import com.seb.account.component.entity.AccountTransactionEntity;
import com.so4it.component.GenericDao;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public interface AccountTransactionDao extends GenericDao<AccountTransactionEntity, String> {
    AccountTransactionEntity getLatest(String arrangementId);
}

