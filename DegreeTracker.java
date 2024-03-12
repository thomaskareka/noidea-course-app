import java.util.ArrayList;

public class DegreeTracker {
    private ArrayList<CourseProgress> studentCourses;
    //private Degree degree;
    private int completedCredits;

    public DegreeTracker(ArrayList<CourseProgress> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public double CalculateGPA() {
        return 0.0;
    }

    public double CalculateMajorGPA() {
        return 0.0;
    }

    public double CalculateProgress() {
        return 0.0;
    }

    public ArrayList<String> GetIncompleteCourses() {
        ArrayList<String> incompletedCourses = new ArrayList<String>();
        for (CourseProgress courseProgress : studentCourses) {
            if(!courseProgress.getCompletionStatus())
                incompletedCourses.add(courseProgress.getCourseName());
        }
        
        return incompletedCourses;
    }

    public ArrayList<String> GetCompleteCourses() {
        ArrayList<String> completedCourses = new ArrayList<String>();
        for (CourseProgress courseProgress : studentCourses) {
            if(courseProgress.getCompletionStatus())
                completedCourses.add(courseProgress.getCourseName());
        }
        
        
        return completedCourses;
    }

    public String generateEightSememsterPlan() {
        return " ";
    }

    public boolean checkIfDegreeRequirementsMet(Degree degree) {
        return true;
    }

    public int requirementCreditsCompleted(String category) {
        return 0;
    }

    public int requirmentCreditsRemaining(String category) {
        return 0;
    }

    public String getAllCourses(){
        String str = "";
        for (CourseProgress courseProgress : studentCourses) {
            str += courseProgress.getCourseName() + "\n";
        }
        return str;
    }

    public String createTranscipt(){
        String str = "";
        for (CourseProgress courseProgress : studentCourses) {
            str += courseProgress.getCourseName() + " - " + courseProgress.getCourseGrade() + "\n";
        }
        return str;
    }
}
