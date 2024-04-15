package model;
public class CourseProgress {
    private String course;
    private Grade gradeReceived;
    private String term;
    private boolean isComplete;


    public CourseProgress(String course, Grade gradeReceived, boolean isComplete) {
        this.course = course;
        this.gradeReceived = gradeReceived;
        this.isComplete = isComplete;
    }

    public Course getCourse(){
        return CourseList.getInstance().getCourseByIdentifer(course);
    }

    public String getCourseID() {
        return course;
    }

    public String getCourseName(){
        Course courseObj = CourseList.getInstance().getCourseByIdentifer(course);
        String str = course + ": " + courseObj.getName();
        return str;
    }

    public boolean getCompletionStatus(){
        return isComplete;
    }

    public String getCourseGrade(){
        String str = gradeReceived.toString().replace("_PLUS", "+");
        return str;
    }
    
    public boolean editCourseGrade(Grade grade){
        gradeReceived = grade;
        isComplete = !grade.equals(Grade.IN_PROGRESS);
        if(gradeReceived != null) {
            System.out.println("Grade successfully edited: " + this.toString());
            return true;
        }
            
        return false;
    }

    public CourseProgress(String course) {
        this.course = course;
    }

    public void makeCourseComplete() {
        isComplete = true;
    }

    public void makeCourseInProgress() {
        gradeReceived = Grade.IN_PROGRESS;
        isComplete = false;
    }

    public void makeCourseUncomplete() {
        isComplete = false;
    }

    public String toString() {
        return course + ": " + gradeReceived.toString();
    }
}
