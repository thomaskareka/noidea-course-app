import java.util.ArrayList;

public class DegreeTracker {
    private ArrayList<CourseProgress> studentCourses;
    //private Degree degree;
    private int completedCredits;
    private int requiredCredits;

    public DegreeTracker(ArrayList<CourseProgress> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public double CalculateGPA() {
        double total = 0.0;
        double gpa = 0.0;
        int totalCredits = 0;
        for (CourseProgress courseProgress : studentCourses) {
            String grade = courseProgress.getCourseGrade();
            int credits = courseProgress.getCourse().getCredits();
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
        double total = 0.0;
        double gpa = 0.0;
        int totalCredits = 0;
        for (CourseProgress courseProgress : studentCourses) {
            String id = courseProgress.getCourse().getIdentifier().substring(0,3);
            if(id == "CSCE") {
                String grade = courseProgress.getCourseGrade();
                int credits = courseProgress.getCourse().getCredits();
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
        }
        
        return gpa;
    }

    public double CalculateProgress() {
        return (completedCredits/requiredCredits)*100;
    }

    public String getCourseGrade(String name, String identifer){
        CourseProgress holder = getCourseProgress(name);
        return holder.getCourseGrade();
    }

    public boolean addGrade(Course course, Grade grade){
        CourseProgress holder = getCourseProgress(course.getName());
        return holder.editCourseGrade(grade);
    }

    private CourseProgress getCourseProgress(String name){
        for (CourseProgress courseProgress : studentCourses) {
            if(courseProgress.getCourseName().equalsIgnoreCase(name))
                return courseProgress;
        }
        return null;
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

    public void addCourse(Course course){
        CourseProgress newCourse = new CourseProgress(course.toString(), null, false);
        studentCourses.add(newCourse);
    }

    public void removeCourse(String courseName){
        for (CourseProgress courseProgress : studentCourses) {
            if(courseProgress.getCourseName().equalsIgnoreCase(courseName))
                studentCourses.remove(courseProgress);
        }
    }

    public String generateEightSememsterPlan() {
        return " ";
    }

    public boolean checkIfDegreeRequirementsMet(Degree degree) {
        if(CalculateProgress() != 100)
            return false;

        return true;
    }

    public int requirementCreditsCompleted(String category) {
        for (CourseProgress courseProgress : studentCourses) {
            String id = courseProgress.getCourse().getIdentifier().substring(0,3);
            if(id == category) {
                if(courseProgress.getCompletionStatus())
                    completedCredits += courseProgress.getCourse().getCredits();
            }
        }
        return completedCredits;
    }

    public int requirementCreditsRemaining(String category, String major) {
        Degree d = DegreeList.getInstance().getMajor(major);
        requiredCredits = d.getCredits();
        return requiredCredits - completedCredits;
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
    //for data saving
    public ArrayList<CourseProgress> getCourseProgress() {
        return studentCourses;
    }
}
