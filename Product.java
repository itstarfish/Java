public class Product {
    private String SKU;
    private String name;
    private String manufacturer;
    private Category category;

    public Product(String SKU, String name, String manufacturer, Category category) {
        this.SKU = SKU;
        this.name = name;
        this.manufacturer = manufacturer;
        this.category = category;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Product [SKU=%s, Name=%s, Manufacturer=%s, Category=%s]", SKU, name, manufacturer, category);
    }
}
