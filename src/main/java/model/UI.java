package model;
import java.util.UUID;

public class UI {

    private CourseSystem system;
    
    public UI(){
        system = CourseSystem.getInstance();
    }

    public void run(){
        // scenario3();
        // scenario4();
        // scenario1();
        // scenario5();
        // scenario6();

        //init_braxScenario();
        //braxScenario();
        //braxScenarioShowChanges();

        init_oddenScenario();
        oddenScenario();
        oddenScenarioShowChanges();
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
    public void init_braxScenario() {
        system.signUp(true, "Brax", "West", "bwest@email.sc.edu", "password");
        system.setStudentMajor("Bachelor of Science in Computer Science");
        system.setStudentID("B12345678");
        system.addGrade("GEOG210", Grade.B);
        system.addGrade("CSCE145", Grade.A);
        system.addGrade("POLI201", Grade.B);
        system.addGrade("MATH141", Grade.B_PLUS);
        system.addGrade("CSCE146", Grade.A);
        system.addGrade("CSCE190", Grade.B_PLUS);
        system.addGrade("ENGL101", Grade.A);
        system.addGrade("PHYS211", Grade.A);
        system.addGrade("MATH142", Grade.C);
        system.addGrade("PHYS211L", Grade.A);
        system.addGrade("CSCE211", Grade.A);
        system.addGrade("CSCE215", Grade.A);
        system.addGrade("ENGL102", Grade.D);
        system.addGrade("MATH241", Grade.A);
        system.addGrade("MUSC114", Grade.A);
        system.addGrade("PHYS212", Grade.B);
        system.addGrade("PHYS212L", Grade.A);
        system.addGrade("CHIN121", Grade.A);
        system.addGrade("CSCE212", Grade.A);
        system.addGrade("CSCE240", Grade.A);
        system.addGrade("MATH374", Grade.B);
        system.addGrade("CSCE247", Grade.A);
        system.addGrade("CSCE311", Grade.A);
        system.addGrade("CSCE350", Grade.A);
        system.addGrade("CSCE390", Grade.A);
        system.addGrade("STAT509", Grade.A);
        system.addGrade("CSCE520", Grade.B);


        system.addCourseForStudent("CSCE490");
        system.addCourseForStudent("MATH344L");
        system.addCourseForStudent("ENGL462");

    }
    public void braxScenario() {
        system.login("bwest@email.sc.edu", "password");
        System.out.println(system.createUserTranscript());
        system.printActiveUser();
        system.printAllRequirements();

        System.out.println(system.getCoursesWithAttribute("CC-GFL"));
        System.out.println(system.getAllApplicationAreas());

        system.setStudentApplicationArea("Digital Design");
        system.addCourseForStudent("MGMT371");
        system.addCourseForStudent("MGMT374");
        system.addCourseForStudent("CSCE520");

        system.printActiveUser();
        system.printAllRequirements();
        System.out.println(system.getEightSemesterPlan());
    }

    public void braxScenarioShowChanges() {
        system.login("bwest@email.sc.edu", "password");
        system.printActiveUser();
        system.printAllRequirements();
    }

    public void init_oddenScenario() {
        system.signUp(true, "Tawnie", "Hill", "thill@email.sc.edu", "notnull");
        system.setStudentID("B00112233");
        system.setStudentMajor("Computer Information Systems");
        system.addGrade("CSCE247", Grade.B);
        system.addGrade("STAT509", Grade.A);
        system.addGrade("STAT512", Grade.A);
        system.addGrade("STAT513", Grade.B_PLUS);
        system.addGrade("GEOG210", Grade.B);
        system.addGrade("CSCE145", Grade.A);
        system.addGrade("POLI201", Grade.B);
        system.addGrade("MATH141", Grade.B_PLUS);
        system.addGrade("CSCE145", Grade.A);
        system.addGrade("CSCE146", Grade.A);
        system.addGrade("CSCE190", Grade.B_PLUS);
        system.addGrade("ENGL101", Grade.A);
        system.addGrade("PHYS211", Grade.A);
        system.addGrade("MATH142", Grade.C);
        system.addGrade("PHYS211L", Grade.A);

        system.logout();
    }

    public void oddenScenario() {
        system.signUp(false, "Osbert", "Odden", "oodd@email.sc.edu", "securepassword12345");
        system.addAdvisee("B00112233");
        system.printActiveStudent();
        System.out.println(system.createUserTranscript());
        
        system.addNote("You have not declared your application area, Statistics would be a great choice.");
        system.printActiveStudent();

        system.logout();
    }

    public void oddenScenarioShowChanges() {
        system.login("oodd@email.sc.edu", "securepassword12345");
        system.chooseActiveStudent("B00112233");
        system.printActiveStudent();
        System.out.println(system.getEightSemesterPlan());
    }
    // public static void main(String[] args){
    //     UI ui = new UI();
    //     ui.run();
    //     ui.exit();
    // }
}
