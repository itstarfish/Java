import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Schedule {
    private List<Consultation> consultations = new ArrayList<>();;

    public void addConsultation (Consultation consultation){
        consultations.add(consultation);
        System.out.println("addConsultation done");
    }

    public boolean checkAvailability(LocalDateTime time, int duration){
        for (Consultation consultation : consultations) {
            if ((consultation.getTime().isEqual(time)) || (consultation.getTime().isBefore(time)) &&
                    (consultation.getTime().plusMinutes(consultation.getDuration()).isAfter(time))) {
                System.out.println("CheckAvailability: false");
                return false;
            }
        }
        System.out.println("CheckAvailability: true");
        return true;
    }

    public List<Consultation> getConsultations(){
        return consultations;
    }



}

