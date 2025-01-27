import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Store {
    private ArrayList<InventoryItem> inventory = new ArrayList<>();
    private ArrayList<Cart> carts = new ArrayList<>();
    private Map<Category, ArrayList<InventoryItem>> aisleInventory = new HashMap<>();

    public void manageStoreCarts(){

    }

    public void checkOutCart(){

    }

    public void abandonCarts(){
        //if date is different than the current date
    }

    public void listProductsByCategory(){

    }
}
