import java.util.ArrayList;

public class CourseList {
    private static CourseList courseList;
    private ArrayList<Course> courses;

    private static CourseList() {
        
    }

    public CourseList getInstance() {
        return courseList;
    }

    public Course getCourse(String subject, int identifier) {
        return null;
    }

    public Course getCourse(String name) {
        return null;
    }

    public ArrayList<Course> getReqCourses(String category) {
        return null;
    }

    public ArrayList<Course> getDegreeCourses(String degree) {
        return null;
    }
}
