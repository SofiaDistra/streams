package social.network;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String Id;
    private List<String> contacts;

    public Person() {

    }

    public Person(String Id) {
        this.Id = Id;
        this.contacts = new ArrayList<>();
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public void addContact(String contact){
        this.contacts.add(contact);
    }
}
