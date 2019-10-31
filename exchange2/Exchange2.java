package exchange2;

import exchangetask.ExchangeInterface;
import exchangetask.Order;
import exchangetask.QueryInterface;
import exchangetask.RequestRejectedException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Exchange2 implements ExchangeInterface, QueryInterface {

  
   public  TreeMap<Integer, Order> buy = new TreeMap<>(Comparator.reverseOrder());
   public  TreeMap<Integer, Order> sell = new TreeMap<>(Comparator.reverseOrder());

    public Exchange2() {
    }

    @Override
    public void send(long orderId, boolean isBuy, int price, int size) throws RequestRejectedException {
        Order order = new Order(orderId, isBuy, price, size);
        if (isBuy) {
            buy.put(order.getPrice(), order);
            // processing between buy and sell orders
            OrderParser2.parser2(buy, sell);
        } else {
            sell.put(order.getPrice(), order);
            // processing between buy and sell orders
            OrderParser2.parser2(buy, sell);
        }
    }

    // The exchange allows traders to cancel their resting orders
    @Override
    public void cancel(long orderId) throws RequestRejectedException {
        // remove orderId from buy list if it does present in it 
        for (int i = 0; i < buy.size(); i++) {
            Integer buyPrice = (Integer) buy.keySet().toArray()[i];
            Order buyOrder = buy.get(buyPrice);
            if (buyOrder.getOrderId() == orderId) {
                buy.remove(buyPrice);
                i--;
            }
        }
        // remove orderId from sell list if it does present in it 
        for (int i = 0; i < sell.size(); i++) {
            Integer buyPrice = (Integer) sell.keySet().toArray()[i];
            Order buyOrder = sell.get(buyPrice);
            if (buyOrder.getOrderId() == orderId) {
                sell.remove(buyPrice);
                i--;
            }
        }
    }

    @Override
    public int getTotalSizeAtPrice(int price)  {
        int p = 0;
        for (Map.Entry<Integer, Order> entry : buy.entrySet()) {
            Order order = entry.getValue();
            if (order.getPrice() == price) {
               p = order.getSize();
            }
        }
        for (Map.Entry<Integer, Order> entry : sell.entrySet()) {
            Order order = entry.getValue();
            if (order.getPrice() == price) {
               p = order.getSize();
            }
        }
        return p;
    }

    @Override
    public int getHighestBuyPrice() throws RequestRejectedException {
        return buy.firstKey();
    }

    @Override
    public int getLowestSellPrice() throws RequestRejectedException {
        return sell.lastKey();
    }

}
