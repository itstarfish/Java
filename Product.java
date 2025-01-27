abstract public class Product implements Discountable{
    private String productName;
    private double price;
    private String category;
    private int stock;

    public Product(String productName, double price, String category, int stock) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    @Override
    public void applyDiscount(double discount) {
        this.price -= this.price * discount / 100;
    }

    @Override
    public void applyBulkDiscount(int qty, double discount) {
        if (qty > 10) {
            this.price -= this.price * discount / 100;
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


}

