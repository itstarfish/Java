import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {
    private List<Person> clients = new ArrayList<>();
    private List<Person> consultants = new ArrayList<>();
    private Schedule schedule = new Schedule();

    public void addClient(Person client){
        clients.add(client);
        System.out.println("addClient done");
    }
    public void addConsultant(Person consultant){
        consultants.add(consultant);
        System.out.println("addConsultant done");
    }
    public Schedule getSchedule(){
        System.out.println("getSchedule: " + schedule);
        return schedule;
    }
}
