import java.util.LinkedList;

public class Order {
    private Integer orderId;
    private LinkedList<OrderItem> orderItems= new LinkedList<>();

    public Order(Integer orderId) {
        this.orderId = orderId;
    }

    public void addProduct(Product product, int qty){
        orderItems.add(new OrderItem(product, qty));
    }

    public double calculateTotal(){
        double total = 0;
        for (OrderItem orderItem:orderItems) {
            total += orderItem.getSubtotal();
        }
        return total;
    }

    public void applyDiscount(double discount){
        for (OrderItem orderItem : orderItems) {
            orderItem.getProduct().applyDiscount(discount);
        }
    }

    public void applyBulkDiscount(int qty, double discount) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getQuantity() >= qty) {
                orderItem.getProduct().applyBulkDiscount(qty, discount);
            }
        }
    }

    public Integer getOrderId() {
        return orderId;
    }

    public LinkedList<OrderItem> getOrderItems() {
        return orderItems;
    }
}

