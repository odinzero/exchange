package exchangetask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exchange implements ExchangeInterface, QueryInterface {

    public List<Order> buy = new ArrayList();
    public List<Order> sell = new ArrayList();

    public Exchange() {
    }

    @Override
    public void send(long orderId, boolean isBuy, int price, int size) throws RequestRejectedException {
        Order order = new Order(orderId, isBuy, price, size);
        if (isBuy) {
            buy.add(order);
            // processing between buy and sell orders
            OrderParser.parser2(buy, sell);
        } else {
            sell.add(order);
            // processing between buy and sell orders
            OrderParser.parser2(buy, sell);
        }
    }

    // The exchange allows traders to cancel their resting orders
    @Override
    public void cancel(long orderId) throws RequestRejectedException {
        // remove orderId from buy list if it does present in it 
        for (int i = 0; i < buy.size(); i++) {
            Order buyOrder = buy.get(i);
            if (buyOrder.getOrderId() == orderId) {
                buy.remove(buyOrder);
                i--;
            }
        }
        // remove orderId from sell list if it does present in it 
        for (int i = 0; i < sell.size(); i++) {
            Order sellOrder = sell.get(i);
            if (sellOrder.getOrderId() == orderId) {
                sell.remove(sellOrder);
                i--;
            }
        }
    }

    @Override
    public int getTotalSizeAtPrice(int price)  {
        int p = 0;
        for (Order buyOrder : buy) {
            if (buyOrder.getPrice() == price) {
                p = buyOrder.getSize();
            }
        }
        for (Order sellOrder : sell) {
            if (sellOrder.getPrice() == price) {
                p = sellOrder.getSize();
            }
        }
        return p;
    }

    @Override
    public int getHighestBuyPrice() throws RequestRejectedException {
        Comparator<Order> compareHighestBuyPrice = (Order o1, Order o2) -> o1.getPrice().compareTo(o2.getPrice());
        Collections.sort(buy, compareHighestBuyPrice.reversed());

        return buy.get(0).getPrice();
    }

    @Override
    public int getLowestSellPrice() throws RequestRejectedException {
        Comparator<Order> compareLowestBuyPrice = (Order o1, Order o2) -> o1.getPrice().compareTo(o2.getPrice());
        Collections.sort(sell, compareLowestBuyPrice);

        return sell.get(0).getPrice();
    }

}
