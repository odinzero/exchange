
package exchangetask2;

import exchangetask.Order;
import java.util.Map;

public class OrderParser2 {
   
     /**
     * This method compares two lists of orders according to price.
     *
     * @param buy -  buy order Map <code>Map<Order></code> .
     * @param sell - sell order Map <code>Map<Order></code>.
     *
     */
    public static void parser2(Map<Integer, Order> buy, Map<Integer, Order>  sell) {
        
        for (int i = 0; i < buy.size(); i++) {
            Integer buyPrice = (Integer) buy.keySet().toArray()[i];
            Order buyOrder = buy.get(buyPrice);
            for (int j = 0; j < sell.size(); j++) {
                Integer sellPrice = (Integer) sell.keySet().toArray()[j];
                Order sellOrder = sell.get(sellPrice);
                // find matching orders
                if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                    if (buyOrder.getSize() != 0 && sellOrder.getSize() != 0) {
                        // do processing size for buy order
                        int qty = buyOrder.getSize() - sellOrder.getSize();

                        if (qty >= 0) {
                            buyOrder.setSize(qty);
                            sellOrder.setSize(0);

                            sell.remove(sellPrice);
                            j--;
                        } else {
                            buyOrder.setSize(0);
                            sellOrder.setSize(Math.abs(qty));

                            buy.remove(buyPrice);
                            i--;
                        }

                        System.out.println(buyOrder.getPrice() + "(" + buyOrder.getSize() + ")" + "  "
                                + sellOrder.getPrice() + "(" + sellOrder.getSize() + ")" + ": "
                                + buyOrder.getSize() + " " + sellOrder.getSize());
                    }
                }

            }
        }

    }
}
