package model;
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
    private String studentID;

    //new student constructor
    public Student(String firstName, String lastName, String email, String major, String password){
        super(firstName, lastName, email, password);
        this.major = major;
        this.degreeProgress = new DegreeTracker(new ArrayList<CourseProgress>());
        //prevent null references
        this.minor = "None";
        this.applicationArea = "None";
        this.majorGPA = 0;
        this.overallGPA = 0;
        this.classLevel = "Freshman";
        this.advisor = new UUID(0L, 0L);
        this.failureRisk = false;
        this.notes = new ArrayList<String>();
        this.hasScholarship = false;
        this.degreeProgress = new DegreeTracker(new ArrayList<CourseProgress>());
        this.studentID = "";
    }

    //loading from JSON files constructor
    public Student(String firstName, String lastName, String email, UUID id, String major, String minor, double majorGPA, double overallGPA, String classLevel, UUID advisor, boolean failureRisk, ArrayList<String> notes, boolean hasScholarship, DegreeTracker degreeProgess, String password, String applicationArea, String studentID){
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
        this.studentID = studentID;
        
        classLevel = getClassLevel();
    }

    public String getTranscript(){
        return degreeProgress.createTranscipt();
    }

    public String getAllCourses(){
        return degreeProgress.getAllCourses();
    }

    public void editFailureRisk(boolean bool){
        failureRisk = bool;
    }

    public boolean getFailureRisk(){
        return failureRisk;
    }

    public boolean checkIfAtRisk(){
        if(overallGPA < 3.00)
            failureRisk = true;
        else
            failureRisk = false;
        
        return failureRisk;
    }

    public String getAllCompletedCourses(){
        ArrayList<String> completedCourse = degreeProgress.GetCompleteCourses();
        String str = "";
        for (String string : completedCourse) {
            str += string + "\n";
        }
        return str;
    }

    public String getAllIncompletedCourses(){
        ArrayList<String> incompletedCourse = degreeProgress.GetIncompleteCourses();
        String str = "";
        for (String string : incompletedCourse) {
            str += string + "\n";
        }
        return str;
    }
    public boolean addCourse(String course){
        return degreeProgress.addCourse(course);
    }
    public boolean removeCourse(String course){
        return degreeProgress.removeCourse(course);
    }
    public String getCourseGrade(String name, String identifier){
       return degreeProgress.getCourseGrade(name, identifier);
    }

    public double getDegreePercentage(Degree degree){
       return degreeProgress.CalculateProgress(degree);
    }
    

    public boolean addGrade(String course, Grade grade){
        if(grade == null){
            System.out.println("No grade was inputted.");
            return false;
        }
        boolean out = degreeProgress.addGrade(course, grade);
        overallGPA = degreeProgress.CalculateGPA();
        majorGPA = degreeProgress.CalculateMajorGPA();
        return out;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        if(DegreeList.getInstance().getMajor(major) != null) {
            this.major = major;
        } else {
            System.out.println("Major does not exist!");
        }
    }

    public void setApplicationArea(String appArea) {
        if(DegreeList.getInstance().getMajor(appArea) != null) {
            this.applicationArea = appArea;
        } else {
            System.out.println("Application Area does not exist!");
        }
    }

    public String getMinor() {
        return minor;
    }

    public double getMajorGPA() {
        return majorGPA;
    }

    public void setOverallGPA(double gpa){
        if(gpa <= 4.0 && gpa >= 0){
            overallGPA = gpa;
        }
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

    public void setAdvisor(UUID advisor) {
        this.advisor = advisor;
    }

    public boolean addNotes(String newNotes){
        if(newNotes != null && !newNotes.equals("")){
            notes.add(newNotes);
            return true;
        }
        return false;
        
        
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public boolean hasScholarship() {
        return hasScholarship;
    }

    public String getApplicationArea() {
        return applicationArea;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String id) {
        studentID = id;
    }

    public DegreeTracker getDegreeTracker() {
        return degreeProgress;
    }
    public String toString() {
        String out = super.toString() + "\n";
        out += String.format("Major: %s (%f), Minor: %s, Application Area: %s\n", major, majorGPA, minor, applicationArea);
        out += "GPA: " + overallGPA + ", At risk: " + failureRisk + ", Has Scholarship: " + hasScholarship + "\n";
        out += "Advisor: " + advisor + ", Student ID: " + studentID + "\n";
        out += "Notes: " + notes.toString();
        return out + "\n";
    }

    public void printAllRequirements() {
        System.out.println(firstName + " " + lastName + " course progress:"); 
        DegreeList degreeList = DegreeList.getInstance();
        Degree ma = degreeList.getMajor(major);
        Degree mi = degreeList.getMajor(minor);
        Degree a = degreeList.getMajor(applicationArea);

        ArrayList<String> courseStrings = degreeProgress.GetCompleteCourses();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<DegreeRequirement> dr = new ArrayList<DegreeRequirement>();
        CourseList cl = CourseList.getInstance();
        for(String s : courseStrings) {
            courses.add(cl.getCourseByIdentifer(s));
        }
        if(ma != null) {
            dr = ma.getRequirements();
            System.out.println(major);
            for (DegreeRequirement degreeRequirement : dr) {
                System.out.println(degreeRequirement.calculateRequirement(courses));
            }
        }  if(mi != null) {
            dr = mi.getRequirements();
            System.out.println(minor);
            for (DegreeRequirement degreeRequirement : dr) {
                System.out.println(degreeRequirement.calculateRequirement(courses));
            }
        } if(a != null) {
            dr = a.getRequirements();
            System.out.println(applicationArea);
            for (DegreeRequirement degreeRequirement : dr) {
                System.out.println(degreeRequirement.calculateRequirement(courses));
            }
        }

    }

    public String getMajorMap() {
        DegreeList degreeList = DegreeList.getInstance();
        Degree ma = degreeList.getMajor(major);
        Degree majorMap = degreeList.getMajorMap(major);

        ArrayList<String> courseStrings = degreeProgress.getCourses();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<DegreeRequirement> majorMapReqs = majorMap.getRequirements();
        ArrayList<DegreeRequirement> majorReqs = ma.getRequirements();
        CourseList cl = CourseList.getInstance();

        for(String s : courseStrings) {
            courses.add(cl.getCourseByIdentifer(s));
        }
        String out = String.format("%s %s (%s)\n", firstName, lastName, major);
        for(DegreeRequirement degreeRequirement : majorMapReqs) {
            out += degreeRequirement.calculateMajorMapSemester(majorReqs, courses, courseStrings, major, applicationArea);
        }

        return out;
    }
}
