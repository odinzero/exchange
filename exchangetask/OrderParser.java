package exchangetask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderParser {

    public OrderParser() {
    }
    
     /**
     * This method compares two lists of orders according to price.
     *
     * @param buy -  buy order list <code>List<Order></code> .
     * @param sell - sell order list <code>List<Order></code>.
     *
     */
    public static void parser2(List<Order> buy, List<Order> sell) {

        sortOrdersInDescendingOrder(buy);
        sortOrdersInDescendingOrder(sell);

         for (int i = 0; i < buy.size(); i++) {
            Order buyOrder = buy.get(i);
            for (int j = 0; j < sell.size(); j++) {
                Order sellOrder = sell.get(j);
                // find matching orders
                if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                    if (buyOrder.getSize() != 0 && sellOrder.getSize() != 0) {
                        // do processing size for buy order
                        int qty = buyOrder.getSize() - sellOrder.getSize();

                        if (qty >= 0) {
                            buyOrder.setSize(qty);
                            sellOrder.setSize(0);
                            
                            sell.remove(sellOrder);
                            j--;
                        } else {
                            buyOrder.setSize(0);
                            sellOrder.setSize(Math.abs(qty));
                            
                            buy.remove(buyOrder);
                            i--;
                        }

                        System.out.println(buyOrder.getPrice() + "(" + buyOrder.getSize() + ")" + "  " + 
                                 sellOrder.getPrice() + "(" + sellOrder.getSize() + ")" +  ": "
                                + buyOrder.getSize() + " " + sellOrder.getSize());
                    }
                }
            }
        }
        
    }

    public static void sortOrdersInDescendingOrder(List<Order> order) {
        Comparator<Order> compareSellByPrice = (Order o1, Order o2) -> o1.getPrice().compareTo(o2.getPrice());
        Collections.sort(order, compareSellByPrice.reversed());


    }

}
