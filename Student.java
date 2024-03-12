import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {
    private String major;
    private String minor;
    private String applicationArea;
    private double majorGPA;
    private double overallGPA;
    private String classLevel;
    private UUID advisor;
    private boolean failureRisk;
    private ArrayList<String> notes;
    private boolean hasScholarship;
    private DegreeTracker degreeProgress;

    //new student constructor
    public Student(String firstName, String lastName, String email, String major, String password){
        super(firstName, lastName, email, password);
        this.major = major;
        this.degreeProgress = new DegreeTracker(new ArrayList<CourseProgress>());
    }

    //loading from JSON files constructor
    public Student(String firstName, String lastName, String email, UUID id, String major, String minor, double majorGPA, double overallGPA, String classLevel, UUID advisor, boolean failureRisk, ArrayList<String> notes, boolean hasScholarship, DegreeTracker degreeProgess, String password, String applicationArea){
        super(firstName, lastName, email, id, password);
        this.major = major; 
        this.minor = minor;
        this.majorGPA = majorGPA;
        this.overallGPA = overallGPA;
        this.classLevel = classLevel;
        this.advisor = advisor;
        this.failureRisk = failureRisk;
        this.notes = notes;
        
        this.hasScholarship = hasScholarship;
        this.applicationArea = applicationArea;
        
        this.degreeProgress = degreeProgess;
        
    }

    public String getTranscript(){
        return degreeProgress.createTranscipt();
    }

    public String getEightSemesterPlan(){
        return "";
    }

    public boolean checkIfAtRisk(){
        return failureRisk;
    }

    public String getAllCompletedCourses(){
        return "";
    }

    public String getAllUncompletedCourses(){
        return "";
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor;
    }

    public double getMajorGPA() {
        return majorGPA;
    }

    public double getOverallGPA() {
        return overallGPA;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public UUID getAdvisorReference() {
        return advisor;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public boolean hasScholarship() {
        return hasScholarship;
    }

    public String toString() {
        String out = super.toString();
        out += String.format("Major: %s (%f), Minor: %s, Application Area: %s\n", major, majorGPA, minor, applicationArea);
        out += "GPA: " + overallGPA + ", At risk: " + failureRisk + ", Has Scholarship: " + hasScholarship;
        out += "Advisor: " + advisor + "\n";
        out += "Notes: " + notes.toString();
        return out + "\n";
    }
}
