//#21
import java.util.ArrayList;

public class UserList {
    private static UserList userlist;
    private ArrayList<User> users;

    private UserList(){
        users = new ArrayList<>();
    }
    public UserList getInstance(){
        if (userlist == null) {
            userlist = new UserList();
        }
        return userlist;
    }
    public User getUser(String email) {
        for (User user : users) {
            if(user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    public boolean containsUser(String email){
        for (User user : users) {
            if(user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public void addUser(User user){       
        users.add(user);
    }
    
}
