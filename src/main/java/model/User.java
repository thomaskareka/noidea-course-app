package model;
import java.util.UUID;

public abstract class User {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected UUID id; 
    protected String password;

    // constuctor for new User
    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = UUID.randomUUID();
        this.password = password;
    }
    
    // constructor for loading users from JSON files
    public User(String firstName, String lastName, String email, UUID id, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public String toString() {
        return firstName + " " + lastName + ": " + email + " (" + id + ")";
    }
}