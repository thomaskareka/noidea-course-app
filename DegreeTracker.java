import java.util.ArrayList;

public class DegreeTracker {
    private ArrayList<CourseProgress> studentCourses;
    //private Degree degree;
    private int completedCredits;

    public DegreeTracker(ArrayList<CourseProgress> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public double CalculateGPA() {
        double total = 0.0;
        double gpa = 0.0;
        int totalCredits = 0;
        for (CourseProgress courseProgress : studentCourses) {
            String grade = courseProgress.getCourseGrade();
            int credits = courseProgress.getCourseCredits(courseProgress.getCourse());
            totalCredits += credits;
            if(grade == "A") 
                total += (4.00 * credits);

            else if (grade == "B_PLUS")
                total += (3.5 * credits);
            
            else if (grade == "B") 
                total += (3.0 * credits);

            else if (grade == "C_PLUS")
                total += (2.5 * credits);
            
            else if (grade == "C")
                total += (2.0 * credits);

            else if (grade == "D_PLUS") 
                total += (1.5 *credits);

            else if (grade == "D")
                total += (1.0 * credits);

            else if (grade == "F") 
                total += (0.0 *credits);
        }
        
        gpa = total/totalCredits;
        
        return gpa;
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
        for (CourseProgress courseProgress : studentCourses) {
            if(courseProgress.getCompletionStatus())
                completedCredits += courseProgress.getCourse().getCredits());
        }
        return completedCredits;
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
