import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFilters {

    public static List<Product> filterByCategory(List<Product> products, String category){
        List<Product> filteredProductsByCategory = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProductsByCategory.add(product);
            }
        }
        return filteredProductsByCategory;
    }

    public static List<Product> filterByPriceRange(List<Product> products, double min, double max){
        List<Product> filteredProductsByPriceRange = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getPrice() >= min && product.getPrice() <= max) {
                filteredProductsByPriceRange.add(product);
            }
        }
        return filteredProductsByPriceRange;
    }
}
