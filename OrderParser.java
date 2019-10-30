package exchangetask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderParser {

    public OrderParser() {
    }

    public static void parser(List<Order> buy, List<Order> sell) {

        sortOrdersInDescendingOrder(buy);
        sortOrdersInDescendingOrder(sell);

        for (Order buyOrder : buy) {
            for (Order sellOrder : sell) {
                // find matching orders
                if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                    if (buyOrder.getSize() != 0 && sellOrder.getSize() != 0) {

                        int qty = buyOrder.getSize() - sellOrder.getSize();

                        if (qty >= 0) {
                            buyOrder.setSize(qty);
                            sellOrder.setSize(0);
                            sell.remove(sellOrder);
                            // matchingSellOrders.add(sellOrder);
                        } else {
                            buyOrder.setSize(0);
                            sellOrder.setSize(Math.abs(qty));

                            //  matchingBuyOrders.add(buyOrder);
                        }

                        System.out.println(buyOrder.getPrice() + "  " + sellOrder.getPrice() + ": "
                                + buyOrder.getSize() + " " + sellOrder.getSize());
                   
                    }
                }
            }
        }
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

//    public static void BuyRestingOrders(List<Order> buy) {
//
//        for (int i = 0; i < buy.size(); i++) {
//            Order buyOrder = buy.get(i);
//            if (buyOrder.getSize() == 0) {
//                buy.remove(buyOrder);
//                i--;
//            }
//        }
//
//        System.out.println("- buy rest");
//        for (Order buyOrder : buy) {
//            System.out.println(buyOrder.getPrice() + " " + buyOrder.getSize());
//        }
//    }

//    public static void SellRestingOrders(List<Order> sell) {
//
//        for (int i = 0; i < sell.size(); i++) {
//            Order sellOrder = sell.get(i);
//            if (sellOrder.getSize() == 0) {
//                sell.remove(sellOrder);
//                i--;
//            }
//        }
//
//        System.out.println("- sell rest");
//        for (Order buyOrder : sell) {
//            System.out.println(buyOrder.getPrice() + " " + buyOrder.getSize());
//        }
//    }

    public static void sortOrdersInDescendingOrder(List<Order> order) {
        Comparator<Order> compareSellByPrice = (Order o1, Order o2) -> o1.getPrice().compareTo(o2.getPrice());
        Collections.sort(order, compareSellByPrice.reversed());

//        for (Order buyOrder : order) {
//            System.out.println(buyOrder.getPrice() + " " + buyOrder.getSize());
//        }
//        
//        System.out.println();
    }

}
