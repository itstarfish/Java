public class Clothing extends Product{
    private int size;

    public Clothing(String productName, double price, String category, int stock, int size) {
        super(productName, price, category, stock);
        this.size = size;
    }

    public void getSizeDetails(){
        System.out.println("Size is: " + size);
    }
}