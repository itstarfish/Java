import java.util.List;
import java.util.ArrayList;

public record Consultant(String name, String lastName, String contact, List<String> specializations) {
    public Consultant(String name, String lastName, String contact) {
        this(name, lastName, contact, new ArrayList<>());
    }
}