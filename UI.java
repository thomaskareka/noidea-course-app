import java.util.UUID;

public class UI {

    private CourseSystem system;
    
    public UI(){
        system = new CourseSystem();
    }

    public void run(){
        // scenario3();
        scenario4();
        scenario1();
        scenario5();
        scenario6();
    }

    public void exit() {
        DataWriter.saveAdvisors();
        DataWriter.saveStudents();
        DataWriter.saveCourses();
        DataWriter.saveMajors();
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
        String userClasses =  system.getAllUserClasses();
        System.out.println(userClasses);
    }

    //create user transcript
    public void scenario6(){

        String transcript = system.createUserTranscript();
        System.out.println(transcript);
    }

    public static void main(String[] args){
        UI ui = new UI();
        ui.run();
        ui.exit();
    }
}
