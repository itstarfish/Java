import java.util.*;

public class Store {
    private ArrayList<InventoryItem> inventory = new ArrayList<>();
    private ArrayList<Cart> carts = new ArrayList<>();
    private Map<Category, ArrayList<InventoryItem>> aisleInventory = new HashMap<>();

    public void manageStoreCarts(){

    }

    public void checkOutCart(Cart cart){
        cart.printSalesSlip();

    }

    public void abandonCarts(){
        Date today = new Date();
        Iterator<Cart> iterator = carts.iterator();

        while (iterator.hasNext()) {
            Cart cart = iterator.next();
            if (cart.getDate().before(today)) {
                iterator.remove();
            }
        }
    }

    public void listProductsByCategory(Category category){
        for (InventoryItem inventoryItem : inventory){
            if (inventoryItem.getProduct().getCategory() == category){
                System.out.println(inventoryItem.getProduct().toString());
            }
        }
    }

    public void addInventoryItem(InventoryItem inventoryItem){
        inventory.add(inventoryItem);
    }

    public void removeInventoryItem(InventoryItem inventoryItem){
        inventory.remove(inventoryItem);
    }
    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }

    public Map<Category, ArrayList<InventoryItem>> getAisleInventory() {
        return aisleInventory;
    }

    public void setAisleInventory(Map<Category, ArrayList<InventoryItem>> aisleInventory) {
        this.aisleInventory = aisleInventory;
    }
}
