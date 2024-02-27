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
        if(!system.login("abc@example.com", "f46fab9f9f91073a4262a6bce61dc3d05ad0a060")) {
            System.out.println("Sorry, you were unable to login.");
            return;
        }
        System.out.println("abc is now logged in");
        system.logout();
        return;
    }

    public void scenario2(){
        if(!system.studentSignUp("Random", "Person", "123@example.com", "Computer Science", "000000000023")){
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
