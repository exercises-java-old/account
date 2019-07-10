package se.lexicon.account.component.service;

import com.lexicon.account.component.domain.Account;
import com.lexicon.account.component.domain.Order;
import com.lexicon.account.component.entity.OrderEntity;
import com.seb.account.componment.dao.OrderDao;
import com.so4it.common.util.object.Required;
import com.so4it.gs.rpc.ServiceExport;
import se.lexicon.account.component.entity.AccountEntity;
import se.lexicon.account.componment.dao.AccountDao;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@ServiceExport({AccountComponentService.class})
public class AccountComponentServiceImpl implements AccountComponentService {

    private final AccountDao accountDao;



    private final OrderDao orderDao;

    public AccountComponentServiceImpl(AccountDao accountDao,OrderDao orderDao) {
        this.accountDao = Required.notNull(accountDao, "accountDao");
        this.orderDao = Required.notNull(orderDao, "orderDao");
    }

    @Override
    public void createAccount(Account account) {
        AccountEntity accountEntity = AccountEntity.builder().withId(account.getSsn()).withAmount(account.getAmount()).build();
        accountDao.insert(accountEntity);


        account.getOrders().stream().map(order -> OrderEntity.builder().withSsn(account.getSsn()).withAmount(order.getAmount()).build())
                .forEach(orderDao::insert);
    }


    @Override
    public Account getAccount(String ssn) {
        AccountEntity accountEntity = accountDao.read(ssn);

        //where ssn =  ssn
        Set<OrderEntity> orderEntities = orderDao.readAll(OrderEntity.templateBuilder().withSsn(ssn).build());


        return Account.builder().withSsn(ssn).withAmount(accountEntity.getAmount()).withId(accountEntity.getId())
                .withOrders(orderEntities.stream().map(entity -> Order.builder().withOrderBookId("").withAmount(entity.getAmount()).build()).collect(Collectors.toSet()))
                .build();

    }


    @Override
    public BigDecimal getTotalAmountOnAccounts() {
        Set<AccountEntity> entities = accountDao.readAll();
        return BigDecimal.valueOf( entities.stream().map( rr -> rr.getAmount().doubleValue()).reduce(0.0,Double::sum));
    }
}