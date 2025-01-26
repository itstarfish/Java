import java.util.ArrayList;
import java.util.Collections;

public class ProductManager {
    private ArrayList<Category> categories = new ArrayList<>();

    public void showAllCategories(){
        for (Category category:categories) {
            System.out.println(category.getName());
        }
    }
    public void addNewCategory(String name){
        this.categories.add(new Category(name));
        System.out.println("New Category with name: " + name + " added");
    }

    public void addNewProductToCategory(Product product, Category category){
        category.addProduct(product);
    }

    public Product getProductByName(String name){
        for (Category category:categories) {
            for (Product product:category.getProducts()) {
                if (product.getName().equalsIgnoreCase(name)){
                    System.out.println("Prekė tokiu pavadinimu rasta:");
                    return product;
                }
            }
        }
        System.out.println("Prekė tokiu pavadinimu nerasta:");
        return null;
    }

    public ArrayList<Product> filterByCategory(String name){
        for (Category category:categories) {
            if (category.getName().equalsIgnoreCase(name)){
                System.out.println("Kategorija tokiu pavadinimu rasta:");
                return category.getProducts();
            }
        }
        System.out.println("Kategorija tokiu pavadinimu nerasta:");
        return null;
    }

    public ArrayList<Product> filterByPriceInterval(double min, double max){
        ArrayList<Product> products = new ArrayList<>();
        for (Category category:categories) {
            for (Product product:category.getProducts()) {
                if (product.getPrice() >= min && product.getPrice() <= max){
                    products.add(product);
                }
            }
        }
        for (Product product:products) {
            product.displayInfo();
        }
        return products;
    }

    public ArrayList<Product> sortByProductPrice(boolean highLow){
        ArrayList<Product> products = new ArrayList<>();
        for (Category category:categories) {
            for (Product product:category.getProducts()) {
                    products.add(product);
            }
        }
        if (highLow)
        Collections.sort(products, (p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
        else
            Collections.sort(products, (p2, p1) -> p1.getPrice().compareTo(p2.getPrice()));
        for (Product product:products) {
            product.displayInfo();
        }
        return products;
    }

    public void showAllProducts(){
        for (Category category: categories) {
            for (Product product:category.getProducts()) {
                product.displayInfo();
            }
        }
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

}
