import java.util.PrimitiveIterator;

abstract class Product{
    public String name;
    public Double price;

    public Product(String name, Double price){
        this.name = name;
        this.price = price;
    }
    public void displayInfo(){
        System.out.printf("%-15s %10.2f%n", this.getName(), this.getPrice());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
