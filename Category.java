import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Product> products = new ArrayList<>();

    public Category(String name){
        this.name = name;
    }
    public void addProduct(Product product){
        this.products.add(product);
    }

    public void showAllProducts(){
        for (Product product:products) {
            product.displayInfo();
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
