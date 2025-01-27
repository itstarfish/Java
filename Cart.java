import java.util.ArrayList;
import java.util.Date;

public class Cart {
    public static int lastId = 0;
    private int id;
    private ArrayList<Product> products = new ArrayList<>();
    private Date date;
    private String type;

    public Cart(int id, String type) {
        this.id = ++lastId;
        this.date = new Date();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
