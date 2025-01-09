import java.time.LocalDateTime;
import java.util.List;

public interface ReservationManager {
    void reserveConsultation(Person client, Person consultant, LocalDateTime time, int duration);
    void cancelReservation(Person client, Consultation consultation);
    List<LocalDateTime> listAvailableTimes(Person consultant);

}
