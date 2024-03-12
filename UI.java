import java.util.UUID;

public class UI {

    private CourseSystem system;
    
    public UI(){
        system = new CourseSystem();
    }

    public void run(){
        // scenario3();
        // scenario4();
        // scenario5();
        scenario6();
    }

    // login
    public void scenario1(){
        if(!system.login("abc@example.com", "f46fab9f9f91073a4262a6bce61dc3d05ad0a060")) {
            System.out.println("Sorry, you were unable to login.");
            return;
        }
        System.out.println("abc is now logged in");
        return;
    }
    
    // sign up
    public void scenario2(){
        // the boolean in signUp method will be true if the user signing up is a student, and false if an advisor.
        if(!system.signUp(true, "random", "random", "random@example.com", "00000003")){
            System.out.println("Sorry, you were unable to signup.");
            return;
        }
        System.out.println("You are now signed up.");
        return;
    }

    //show all classes
    public void scenario3(){
        String allClasses = system.showAllCourses();
        System.out.println(allClasses);
    }

    // show course by code
    public void scenario4(){
        String course = system.showCourseByCode("CSCE145");
        System.out.println(course);
    }

    //show all user classes
    public void scenario5(){
        scenario1();

    }

    //create user transcript
    public void scenario6(){
        UUID id = UUID.fromString("3679308a-dda8-4a27-8260-8fd115426bd0");
        String transcript = system.createUserTranscript(id);
        System.out.println(transcript);
    }

    public static void main(String[] args){
        UI ui = new UI();
        ui.run();
    }
}
