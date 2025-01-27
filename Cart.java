import java.util.ArrayList;
import java.util.Date;

public class Cart {
    private int id;
    private ArrayList<Product> products = new ArrayList<>();
    private Date date;
    private String type;

    public Cart(int id, Date date, String type) {
        this.id = id;
        this.date = date;
        this.type = type;
    }

    public void addItem(Product product){
        products.add(product);
    }

    public void removeItem(Product product){
        products.remove(product);
    }

    public void printSalesSlip(){
        System.out.println("Sales Slip");
        for (Product product:products) {
            System.out.println(product.toString());
        }
    }
}
