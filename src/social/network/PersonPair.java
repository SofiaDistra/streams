package social.network;

public class PersonPair extends Person{

    private String otherId;

    public PersonPair() {
        super();
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getFullId() {
        return getId() + "," + otherId;
    }
}
