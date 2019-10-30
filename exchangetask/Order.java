
package exchangetask;

public class Order  {
    
     private Long orderId;
     private boolean isBuy;
     private Integer price; 
     private Integer size;
    
    Order(long orderId, boolean isBuy, int price, int size) {
       this.orderId = orderId;
       this.isBuy   = isBuy;
       this.price   = price;
       this.size    = size;
    }
    
    

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
    
    public void setIsBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    public boolean isIsBuy() {
        return isBuy;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }
    
     @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                "isBuy=" + isBuy + '\'' +
                ", price='" + price + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
    
}
