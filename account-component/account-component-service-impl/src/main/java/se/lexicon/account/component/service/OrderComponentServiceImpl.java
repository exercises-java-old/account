package se.lexicon.account.component.service;

import com.lexicon.account.component.entity.OrderEntity;
import com.lexicon.account.component.event.PlaceOrderEvent;
import com.so4it.common.util.object.Required;
import com.so4it.gs.rpc.ServiceExport;
import com.so4it.queue.ParallelQueue;
import se.lexicon.account.component.domain.Order;
import se.lexicon.account.component.domain.Orders;
import se.lexicon.account.componment.dao.OrderDao;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@ServiceExport({OrderComponentService.class})
public class OrderComponentServiceImpl implements OrderComponentService {

    private OrderDao orderDao;

    private ParallelQueue<PlaceOrderEvent> orderParallelQueue;

    public OrderComponentServiceImpl(OrderDao orderDao,ParallelQueue<PlaceOrderEvent> orderParallelQueue) {
        this.orderDao = Required.notNull(orderDao, "orderDao");
        this.orderParallelQueue = Required.notNull(orderParallelQueue,"orderParallelQueue");
    }

    @Override
    public Orders getOrders(String ssn) {
        return Orders.valueOf(orderDao.readAll(OrderEntity.templateBuilder().withSsn(ssn).build()).stream().
                map(entity -> Order.builder()
                        .withId(entity.getId())
                        .withSsn(ssn)
                        .withAmount(entity.getAmount())
                        .withOrderBookId("").build()).collect(Collectors.toSet()));
    }


    @Override
    public void placeOrder(Order order) {
        orderParallelQueue.offer(PlaceOrderEvent.templateBuilder()
                .withSsn(order.getSsn())
                .withAmount(order.getAmount())
                .build());
    }


    @Override
    public BigDecimal getTotalOrderValueOfAllAccounts() {
        return orderDao.sum();
    }
}