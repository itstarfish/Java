import java.util.List;
import java.util.ArrayList;

public record Client(String name, String lastName, String contact, List<Consultation> reservations) {
    public Client(String name, String lastName, String contact) {
        this(name, lastName, contact, new ArrayList<>());
    }
}