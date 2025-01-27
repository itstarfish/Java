public class Main {
    public static void main(String[] args) {

        Product pc = new Electronics("PC", 1800.00, "Electronics", 10, "2 years");
        Product iphone = new Electronics("Iphone", 600.00, "Electronics", 5, "1 year");
        Product shorts = new Clothing("Shorts", 20.00, "Clothing", 50, 42);
        Product tshirt = new Clothing("Tshirt", 10.00, "Clothing", 30, 34);

        Customer c1 = new Customer(1,"Jonas Jonaitis", "Jonas@gmail.com");
        Customer c2 = new Customer(2, "Linas Linaitis", "Linas@gmail.com");

        Order o1 = new Order(1);
        o1.addProduct(pc, 1);
        o1.addProduct(shorts, 5);

        Order o2 = new Order(2);
        o2.addProduct(iphone, 2);
        o2.addProduct(tshirt, 3);

        c1.placeOrder(o1);
        c2.placeOrder(o2);

        StoreManager storeManager = new StoreManager();
        storeManager.getProducts().add(pc);
        storeManager.getProducts().add(iphone);
        storeManager.getProducts().add(shorts);
        storeManager.getProducts().add(tshirt);
        storeManager.getCustomers().add(c1);
        storeManager.getCustomers().add(c2);

        storeManager.generateReport();

        o1.applyDiscount(10);
        o2.applyBulkDiscount(2, 15);

        storeManager.removeOutOfStock();

        storeManager.generateReport();

        c1.getOrderStatistics();
        c1.removeOrder(1);
        System.out.println("After removing order:");
        c1.getOrderStatistics();
    }
}