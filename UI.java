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
        if(system.logout()){
            System.out.println("You have successfully logged out.");
        }
        return;
    }

    public void scenario2(){
        // the boolean in signUp method will be true if the user signing up is a student, and false if an advisor.
        if(!system.signUp(true, "random", "random", "random@example.com", "00000003")){
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
