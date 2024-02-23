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
    public User getUser(String id) {

    }
    
}
