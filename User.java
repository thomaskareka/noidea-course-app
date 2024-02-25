import java.util.UUID;

public abstract class User {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected UUID id; 

    // constuctor for new User
    public User(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        id = new UUID(18, 18);
    }
    
    // constructor for loading users from JSON files
    public User(String firstName, String lastName, String email, UUID id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public UUID getID(){
        return id;
    }
}