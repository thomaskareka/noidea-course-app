import java.util.UUID;

public abstract class User {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected UUID id; 

    public User(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        id = new UUID(18, 18);
    }
    
    public User(String firstName, String lastName, String email, UUID id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }
}