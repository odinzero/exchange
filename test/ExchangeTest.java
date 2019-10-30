/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangetask;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ZEROZ
 */
public class ExchangeTest {

    ArrayList<Long> orderId_buy = utils.getUniqueRandomNumberInRange(5, 5L);
    ArrayList<Long> orderId_sell = utils.getUniqueRandomNumberInRange(5, 5L);

    public ExchangeTest() {
    }

    public Exchange downloadSellOrders() throws RequestRejectedException {
        // sell
        int[] price_sell = {500, 650, 555, 300, 800};
        int[] size_sell = {20, 10, 15, 12, 9};

        Exchange exchange = new Exchange();

        // download sell orders
        for (int i = 0; i < orderId_sell.size(); i++) {

            exchange.send(orderId_sell.get(i), false, price_sell[i], size_sell[i]);
        }
        return exchange;
    }

    public Exchange downloadBuyOrders() throws RequestRejectedException {
        // buy
        int[] price_buy = {300, 250, 725, 915, 80};
        int[] size_buy = {21, 8, 32, 11, 6};

        Exchange exchange = new Exchange();

        // download sell orders
        for (int i = 0; i < orderId_buy.size(); i++) {

            exchange.send(orderId_buy.get(i), true, price_buy[i], size_buy[i]);
        }
        return exchange;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of send method, of class Exchange.
     */
    public void init() throws RequestRejectedException {
        // sell
        int[] price_sell = {500, 650, 555, 300, 800};
        int[] size_sell = {20, 10, 15, 12, 9};

        Exchange exchange = new Exchange();

        // download sell orders
        for (int i = 0; i < orderId_sell.size(); i++) {

            exchange.send(orderId_sell.get(i), false, price_sell[i], size_sell[i]);
        }
    }

    /**
     * Test of send method, of class Exchange.
     */
    @Test
    public void testSend() throws Exception {
        System.out.println("================ Send =============================");
        // sell
        int[] price_sell = {500, 650, 555, 300, 800};
        int[] size_sell = {20, 10, 15, 12, 9};
        // buy
        int[] price_buy = {300, 250, 725, 915, 80};
        int[] size_buy = {21, 8, 32, 11, 6};

        Exchange exchange = new Exchange();

        // download sell orders
        for (int i = 0; i < orderId_sell.size(); i++) {

            exchange.send(orderId_sell.get(i), false, price_sell[i], size_sell[i]);
        }
        // 1. we downloaded SELL ORDERS and structure for this example 
        // like this(parser does not working because BUY ORDERS do not downloaded ):
        
        //SELL ORDERS BEFORE EXCHANGE
        //800 9
        //650 10
        //555 15
        //500 20
        //300 12
        
        
        // 2. when we send buy orders then  buy order price  is 
        // compared with sell order price and buy and sell orders are processed 
        // according to requirements
        
        // download queue  for buy orders       
        // 300 -> 250 -> 725 -> 915 -> 80

        //  before matching       after matching 
        //------------------------------------------
        // buy(size) sell(size)  buy_size  sell_size
        // 300(21)   300(12):     9           0    
        // 725(32)   650(10):     22          0
        // 725(22)   555(15):     7           0
        // 725(7)    500(20):     0          13
        // 915(11)   800(9):      2           0
        // 915(2)    500(13):     0          11 

        // download buy orders 
        for (int i = 0; i < orderId_buy.size(); i++) {
            // do exchange if price price of the Buy order is higher than, 
            // or equal to, the price of the Sell order
            exchange.send(orderId_buy.get(i), true, price_buy[i], size_buy[i]);
        }
    }

    /**
     * Test of cancel method, of class Exchange.
     */
    @Test
    public void testCancel_forSell() throws Exception {
        System.out.println("================ Cancel for SELL =============================");

        Exchange exchange = downloadSellOrders();

        System.out.println("sell size before cancel :" + exchange.sell.size());

        // The exchange allows traders to cancel their resting orders( sell orders)
        for (int i = 0; i < orderId_sell.size(); i++) {

            exchange.cancel(orderId_sell.get(i));
        }

        System.out.println("sell size after cancel :" + exchange.sell.size());
    }

    /**
     * Test of cancel method, of class Exchange.
     */
    @Test
    public void testCancel_forBuy() throws Exception {
        System.out.println("================ Cancel for BUY =============================");

        Exchange exchange = downloadBuyOrders();

        System.out.println("buy size before cancel :" + exchange.buy.size());

        // The exchange allows traders to cancel their resting orders( buy orders)
        for (int i = 0; i < orderId_buy.size(); i++) {

            exchange.cancel(orderId_buy.get(i));
        }

        System.out.println("buy size after cancel :" + exchange.buy.size());
    }

    /**
     * Test of getTotalSizeAtPrice method, of class Exchange.
     */
    @Test
    public void testGetTotalSizeAtPrice_forSell() throws RequestRejectedException {
        System.out.println("========== getTotalSizeAtPrice for SELL ==========");

        Exchange exchange = downloadSellOrders();

        System.out.println("sell TotalSizeAtPrice :" + exchange.getTotalSizeAtPrice(500));
        System.out.println("sell TotalSizeAtPrice if not exists :" + exchange.getTotalSizeAtPrice(1000000));
    }

    /**
     * Test of getTotalSizeAtPrice method, of class Exchange.
     */
    @Test
    public void testGetTotalSizeAtPrice_forBuy() throws RequestRejectedException {
        System.out.println("========== getTotalSizeAtPrice for BUY ==========");

        Exchange exchange = downloadBuyOrders();

        System.out.println("buy TotalSizeAtPrice :" + exchange.getTotalSizeAtPrice(300));
        System.out.println("buy TotalSizeAtPrice if not exists :" + exchange.getTotalSizeAtPrice(1000000));
    }

    /**
     * Test of getHighestBuyPrice method, of class Exchange.
     */
    @Test
    public void testGetHighestBuyPrice() throws Exception {
        System.out.println("========== getHighestBuyPrice ==========");

        Exchange exchange = downloadBuyOrders();

        System.out.println("GetHighestBuyPrice :" + exchange.getHighestBuyPrice());
    }

    /**
     * Test of getLowestSellPrice method, of class Exchange.
     */
    @Test
    public void testGetLowestSellPrice() throws Exception {
        System.out.println("========== getLowestSellPrice ========== ");

        Exchange exchange = downloadSellOrders();

        System.out.println("GetLowestSellPrice :" + exchange.getLowestSellPrice());
    }

}
