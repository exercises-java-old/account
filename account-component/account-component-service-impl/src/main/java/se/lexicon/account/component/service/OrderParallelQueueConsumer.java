package se.lexicon.account.component.service;

import com.lexicon.account.component.entity.OrderEntity;
import com.lexicon.account.component.event.PlaceOrderEvent;
import com.so4it.queue.ParallelQueueConsumer;
import se.lexicon.account.componment.dao.OrderDao;

public class OrderParallelQueueConsumer {


    private OrderDao orderDao;

    //private MarketApiClient marketClient;


    public OrderParallelQueueConsumer(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * This will be called by the parallel queue framework guaranteeing that only one order for the same
     * accoutn id will be handled at the time
     *
     * @param orderEntity
     */
    @ParallelQueueConsumer
    public void onOrder(PlaceOrderEvent orderEntity) {

        System.out.println("AAAAAAAAAA\n\n\n");
        //
        //String marketId = marketClient.doMatch(PlaceOrderMathRequest.builder(),);


        orderDao.insert(OrderEntity.builder().withSsn(orderEntity.getSsn()).withAmount(orderEntity.getAmount()).build());
    }
}
