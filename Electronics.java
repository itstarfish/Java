public class Electronics extends Product{
    private String warrantyPeriod;

    public Electronics(String productName, double price, String category, int stock, String warrantyPeriod) {
        super(productName, price, category, stock);
        this.warrantyPeriod = warrantyPeriod;
    }

    public void checkWarranty(){
        System.out.println("Product warranty is: " + warrantyPeriod);
    }
}