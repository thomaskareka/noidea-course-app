import java.util.ArrayList;
import java.util.UUID;

public class Student extends User{
    private String major;
    private String minor;
    private double majorGPA;
    private double overallGPA;
    private String classLevel;
    private Advisor advisor;
    private boolean failureRisk;
    private ArrayList<String> notes;
    private boolean hasScholorship;
    private DegreeTracker degreeProgress;

    public Student(String firstName, String lastName, String email, String major){
        super(firstName, lastName, email);
        this.major = major;
    }

    public Student(String firstName, String lastName, String email, UUID id, String major, String minor, double majorGPA, double overallGPA, String classLevel, Advisor advisor, boolean failureRisk, ArrayList<String> notes, boolean hasScholarship, DegreeTracker degreeProgess){
        super(firstName, lastName, email, id);
        this.major = major; 
        this.minor = minor;
        this.majorGPA = majorGPA;
        this.overallGPA = overallGPA;
        this.classLevel = classLevel;
        this.advisor = advisor;
        this.failureRisk = failureRisk;
        this.notes = notes;
        
        if(hasScholarship)
            this.hasScholorship = true;
        else
            this.hasScholorship = false;
        
        this.degreeProgress = degreeProgress;
        
    }

    public String generateEightSemesterPlan(){
        return "";
    }

    public boolean checkIfAtRisk(){
        return true;
    }

    public String getAllCompletedCourses(){
        return "";
    }

    public String getAllUncompletedCourses(){
        return "";
    }

}
