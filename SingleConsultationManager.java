import java.time.LocalDateTime;
import java.util.List;


public class SingleConsultationManager implements ReservationManager {
    private ReservationSystem reservationSystem;

    public SingleConsultationManager(ReservationSystem reservationSystem){
        this.reservationSystem = reservationSystem;
    }

    @Override
    public void reserveConsultation(Person client, Person consultant, LocalDateTime time, int duration){
        if (reservationSystem.getSchedule().checkAvailability(time, duration)){
            Consultation consultantion = new Consultation(consultant, client, time, duration, "Planuojama");
            reservationSystem.getSchedule().addConsultation(consultantion);
        }
        System.out.println("reserveConsultation done");
    }

    @Override
    public void cancelReservation(Person client, Consultation consultation){
        reservationSystem.getSchedule().getConsultations().remove(consultation);
        System.out.println("cancelReservation done");
    }

    @Override
    public List <LocalDateTime> listAvailableTimes(Person consultant){
        return null;
    }
}
