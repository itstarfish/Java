import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StoreManager {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();

    public void generateReport(){
        double totalSales = calculateTotalSales();
        Map<Product, Integer> topProducts = calculateTopProductsBySales();

        System.out.println("Total sales: " + totalSales);
        System.out.println("Products by sales:");
        for (Product product : topProducts.keySet()) {
            System.out.println(product.getProductName() + ": " + topProducts.get(product) + " sold");
        }
    }

    public void removeOutOfStock(){
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getStock() == 0) {
                iterator.remove();
            }
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }


    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    private double calculateTotalSales() {
        double totalSales = 0;
        for (Customer customer : customers) {
            for (Order order : customer.getOrders()) {
                totalSales += order.calculateTotal();
            }
        }
        return totalSales;
    }

    private Map<Product, Integer> calculateTopProductsBySales() {
        Map<Product, Integer> productSales = new HashMap<>();
        for (Customer customer : customers) {
            for (Order order : customer.getOrders()) {
                for (OrderItem orderItem : order.getOrderItems()) {
                    Product product = orderItem.getProduct();
                    int quantity = orderItem.getQuantity();
                    productSales.put(product, productSales.getOrDefault(product, 0) + quantity);
                }
            }
        }
        return productSales;
    }
}
