import java.util.Iterator;
import java.util.LinkedList;

public class Customer {
    private int customerID;
    private String name;
    private String email;
    private LinkedList<Order> orders = new LinkedList<>();

    public Customer(int customerID, String name, String email) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
    }

    public void placeOrder(Order order){
        orders.add(order);
    }

    public void removeOrder(Integer orderId){
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOrderId().equals(orderId)) {
                iterator.remove();
            }
        }
    }

    public void getOrderStatistics(){
        int totalOrders = orders.size();
        double totalAmount = 0;

        System.out.println("Order statistics for " + name + ":");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            for (OrderItem orderItem : order.getOrderItems()) {
                System.out.println("Product: " + orderItem.getProduct().getProductName() +
                        ", Quantity: " + orderItem.getQuantity() +
                        ", Subtotal: " + orderItem.getSubtotal());
            }
            double orderTotal = order.calculateTotal();
            totalAmount += orderTotal;
            System.out.println("Order Total: " + orderTotal);
            System.out.println();
        }
    }


    public LinkedList<Order> getOrders() {
        return orders;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

