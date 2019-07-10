package com.seb.account.component.dao;

import com.lexicon.account.component.entity.AccountEntity;
import com.lexicon.account.component.entity.OrderEntity;
import com.seb.account.componment.dao.AccountDao;
import com.seb.account.componment.dao.OrderDao;
import com.so4it.component.dao.gs.AbstractSpaceDao;
import org.openspaces.core.GigaSpace;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public class OrderDaoImpl extends AbstractSpaceDao<OrderEntity, String> implements OrderDao {

    public OrderDaoImpl(GigaSpace gigaSpace) {
        super(gigaSpace);
    }
}



