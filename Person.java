import java.util.ArrayList;
import java.util.List;

public class Person<T> {
    private String name;
    private String lastName;
    private String contact;
    private List<T> list = new ArrayList<>();

    public Person(String name, String lastName, String contact){
        this.name = name;
        this.lastName = lastName;
        this.contact = contact;
    }

    public void addToList(T t){
        if (!list.contains(t)){
            list.add(t);
        }
    }

    public void getList(){
        System.out.println(name + " " + lastName);
        System.out.println(list);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", list=" + list +
                '}';
    }
}
