public class UI {

    private CourseSystem system;
    
    public UI(){
        system = new CourseSystem();
    }

    public void run(){
        scenario1();
        scenario2();
    }

    public void scenario1(){
        if(!system.login("abc", "abc")) {
            System.out.println("Sorry we couldn't login.");
            return;
        }
        System.out.println("abc is nw logged in");
        system.logout();
        return;
    }

    public void scenario2(){
        if(!system.studentSignUp("abc", "abc", "abc", "abc", "abc")){
            System.out.println("Sorry, you were unable to signup.");
            return;
        }
        System.out.println("You are now signed up.");
        return;
    }

    public static void main(String[] args){
        UI ui = new UI();
        ui.run();
    }
}
