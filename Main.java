import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ReservationSystem system = new ReservationSystem();

        Person<Consultation> personClient = new Person<>("Tadas", "Tadauskas", "tadas@gmail.com");
        Person<String> personConsultant = new Person<>("Kestas", "Kestaitis", "kestas@gmail.com");
        personClient.addToList(new Consultation(personConsultant, personClient, LocalDateTime.now(),60, "Planuojama"));
        System.out.println(personClient);


        personConsultant.addToList("Matematika");
        personConsultant.addToList("Fizika");
        System.out.println(personConsultant);

        ReservationManager manager = new SingleConsultationManager(system);
        manager.reserveConsultation(personClient, personConsultant, LocalDateTime.now().plusDays(1), 60);
        manager.reserveConsultation(personClient, personConsultant, LocalDateTime.now().plusDays(1), 60);

        List<LocalDateTime> availableTimes = manager.listAvailableTimes(personConsultant);


    }



}