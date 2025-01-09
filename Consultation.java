import java.time.LocalDateTime;

public class Consultation {
    private Person consultant;
    private Person client;
    private LocalDateTime time;
    private int duration;
    private String status;

    public Consultation(Person consultant, Person client, LocalDateTime time, int duration, String status){
        this.consultant = consultant;
        this.client = client;
        this.time = time;
        this.duration = duration;
        this.status = status;
    }

    public Person getConsultant() {
        return consultant;
    }

    public void setConsultant(Person consultant) {
        this.consultant = consultant;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
